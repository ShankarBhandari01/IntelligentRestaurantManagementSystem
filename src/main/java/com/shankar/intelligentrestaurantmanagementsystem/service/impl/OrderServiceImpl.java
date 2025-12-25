package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Customer;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.OrderMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.CustomerRepository;
import com.shankar.intelligentrestaurantmanagementsystem.repository.OrderRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.KotService;
import com.shankar.intelligentrestaurantmanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
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

    private final OrderMapper orderMapper;
    private final KotService kotService;

    @Override
    @Transactional
    public CompletableFuture<Void> processOrder(OrderRequest orderRequest) {
        Customer incoming = orderRequest.getCustomer();

        Customer customer = customerRepository
                .findByEmail(incoming.getEmail())
                .orElseGet(() -> customerRepository.save(incoming));

        Order order = orderMapper.toEntity(orderRequest);
        order.setCustomer(customer);

        var saveOrder = orderRepository.save(order);

        List<KotItem> kotItems = order.getItems().stream()
                .map(o ->
                        new KotItem(
                                null,
                                o.getItem(),
                                o.getQuantity(),
                                o.getSpecial_requests()
                        )
                )
                .toList();

        // while saving kot need reference of order
        Kot kot = Kot.builder()
                .status(KotStatus.CREATED)
                .orderId(saveOrder.getId())
                .deviceId("")
                .createdAt(Instant.now())
                .items(kotItems)
                .build();

        kotService.sendOrderToKot(kot);

        return CompletableFuture.completedFuture(null);
    }
}
