package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.domain.OrderSpecification;
import com.shankar.intelligentrestaurantmanagementsystem.dto.KotItemStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderItemStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderFilterRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderItemsDTO;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.PaginatedResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.*;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.CustomerMapper;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.OrderMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.CustomerRepository;
import com.shankar.intelligentrestaurantmanagementsystem.repository.MenuItemRepository;
import com.shankar.intelligentrestaurantmanagementsystem.repository.OrderRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.INumberGeneratorService;
import com.shankar.intelligentrestaurantmanagementsystem.service.IKotService;
import com.shankar.intelligentrestaurantmanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MenuItemRepository menuItemRepository;
    private final IKotService kotService;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final INumberGeneratorService numberGeneratorService;

    @Override
    @Transactional
    @Async
    public CompletableFuture<OrderResponse> processOrder(OrderRequest orderRequest) {
        try {
            Customer incoming = customerMapper.toEntity(orderRequest.getCustomer());
            Customer customer = customerRepository
                    .findByEmail(incoming.getEmail())
                    .orElseGet(() -> customerRepository.save(incoming));

            BigDecimal totalAmount = BigDecimal.ZERO;

            String orderNumber = numberGeneratorService.generateOrderNumber();
            Order order = orderMapper.toEntity(orderRequest);
            order.setOrderNumber(orderNumber);
            order.setCustomer(customer);

            List<OrderItems> orderItemsList = new ArrayList<>();

            for (OrderItemsDTO itemDTO : orderRequest.getItems()) {
                // Fetch managed MenuItem from DB
                MenuItems menuItem = menuItemRepository.findById(itemDTO.getMenuItemId())
                        .orElseThrow(() -> new RuntimeException("MenuItem not found: " + itemDTO.getMenuItemId()));

                OrderItems orderItem = OrderItems.builder()
                        .order(order)
                        .menuItem(menuItem)
                        .quantity(itemDTO.getQuantity())
                        .special_requests(itemDTO.getSpecial_requests())
                        .pricePerItem(menuItem.getAmount())
                        .totalPrice(menuItem.getAmount().multiply(BigDecimal.valueOf(itemDTO.getQuantity())))
                        .status(OrderItemStatus.QUEUED)
                        .station(menuItem.getStation())
                        .build();

                totalAmount = totalAmount.add(orderItem.getTotalPrice());
                orderItemsList.add(orderItem);

            }
            order.setItems(new HashSet<>(orderItemsList));
            order.setTotalAmount(totalAmount);

            var savedOrder = orderRepository.save(order);
            // return saved order
            OrderResponse orderResponse = orderMapper.toResponse(savedOrder);

            if (savedOrder.getStatus().equals(OrderStatus.NEW)) {

                Map<Station, List<OrderItems>> itemsByStation = savedOrder.getItems().stream()
                        .collect(Collectors.groupingBy(i -> i.getMenuItem().getStation()));

                List<Kot> kotList = new ArrayList<>();

                for (Map.Entry<Station, List<OrderItems>> entry : itemsByStation.entrySet()) {
                    Station station = entry.getKey();
                    List<OrderItems> items = entry.getValue();

                    Kot kot = Kot.builder()
                            .order(savedOrder)
                            .kotNumber(numberGeneratorService.generateKotNumber())
                            .station(station)
                            .deviceId("112434343")
                            .status(KotStatus.QUEUED)
                            .build();

                    Kot savedKot = kotService.sendOrderToKot(kot).get(); // save header first

                    // Create KotItems
                    List<KotItem> kotItems = items.stream().map(item -> KotItem.builder()
                                    .kot(savedKot)
                                    .orderItem(item)
                                    .quantity(item.getQuantity())
                                    .specialRequests(item.getSpecial_requests())
                                    .status(KotItemStatus.NEW)
                                    .build())
                            .toList();

                    kotService.saveKotItems(kotItems);

                    kotList.add(savedKot);
                }

                orderResponse.setKots(kotList);
            }

            return CompletableFuture.completedFuture(orderResponse);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<OrderResponse> getOrderById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        var response = orderMapper.toResponse(order);

        return CompletableFuture.completedFuture(response);
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<PaginatedResponse<OrderResponse>> getAllOrders(OrderFilterRequest filter, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<Order> orderPage = orderRepository.findAll(
                OrderSpecification.filter(filter),
                pageable
        );

        List<OrderResponse> content = orderPage.getContent()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
        var response = PaginatedResponse.<OrderResponse>builder()
                .content(content)
                .page(orderPage.getNumber())
                .size(orderPage.getSize())
                .totalElements(orderPage.getTotalElements())
                .totalPages(orderPage.getTotalPages())
                .last(orderPage.isLast())
                .build();

        return CompletableFuture.completedFuture(response);

    }

    @Async
    @Override
    public CompletableFuture<OrderResponse> updateOrder(Long id, OrderRequest request) {
        return null;
    }
}
