package com.shankar.intelligentrestaurantmanagementsystem.mapper;


import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<Order, OrderRequest, OrderResponse> {
    private final CustomerMapper customerMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public Order toEntity(@NonNull OrderRequest req) {
        return Order.builder()
                .items(req.getItems().stream().map(orderItemMapper::toEntity).collect(Collectors.toSet()))
                .orderRemarks(req.getOrderRemarks())
                .orderType(req.getOrderType())
                .totalAmount(req.getTotalAmount())
                .status(OrderStatus.NEW)
                .build();
    }

    @Override
    public OrderResponse toResponse(@NonNull Order entity) {
        return OrderResponse.builder()
                .items(entity.getItems().stream().map(orderItemMapper::toResponse).collect(Collectors.toSet()))
                .customer(customerMapper.toResponse(entity.getCustomer()))
                .orderRemarks(entity.getOrderRemarks())
                .orderType(entity.getOrderType())
                .totalAmount(entity.getTotalAmount())
                .id(entity.getId())
                .status(entity.getStatus() == null ? OrderStatus.NEW : entity.getStatus())
                .build();
    }

}
