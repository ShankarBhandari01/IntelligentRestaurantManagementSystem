package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.domain.ProcessOrder;
import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Customer;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.CustomerMapper;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.OrderMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.CustomerRepository;
import com.shankar.intelligentrestaurantmanagementsystem.repository.OrderRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.KotService;
import com.shankar.intelligentrestaurantmanagementsystem.service.MenuItemService;
import com.shankar.intelligentrestaurantmanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MenuItemService menuItemService;
    private final KotService kotService;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    @Async
    public CompletableFuture<OrderResponse> processOrder(OrderRequest orderRequest) {
        try {
            ProcessOrder processOrder = new ProcessOrder(orderRequest, orderMapper);

            Customer incoming = customerMapper.toEntity(processOrder.getCustomerDto());

            Customer customer = customerRepository
                    .findByEmail(incoming.getEmail())
                    .orElseGet(() -> customerRepository.save(incoming));

            Order order = processOrder.getOrder();
            order.setCustomer(customer);

            var saveOrder = orderRepository.save(order);
            // return saved order
            OrderResponse orderResponse = orderMapper.toResponse(saveOrder);

            if (saveOrder.getStatus().equals(OrderStatus.PROCESSING)) {
                Kot kot = processOrder.generateKot();
                kot.setOrderId(saveOrder.getId());
                Kot savedKot = kotService.sendOrderToKot(kot).get();
                orderResponse.setKot(savedKot);
            }
            return CompletableFuture.completedFuture(orderResponse);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Async
    @Override
    public CompletableFuture<OrderResponse> getOrderById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        var response = orderMapper.toResponse(order);

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<List<OrderResponse>> getAllOrders() {
        return null;
    }

    @Override
    public CompletableFuture<OrderResponse> updateOrder(Long id, OrderRequest request) {
        return null;
    }
}
