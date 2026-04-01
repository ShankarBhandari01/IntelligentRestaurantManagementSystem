package com.shankar.intelligentrestaurantmanagementsystem.mapper;


import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<Order, OrderRequest, OrderResponse> {
    private final CustomerMapper customerMapper;

    @Override
    public Order toEntity(@NonNull OrderRequest req) {
        return Order.builder()
                .items(req.getItems())
                .orderRemarks(req.getOrderRemarks())
                .orderType(req.getOrderType())
                .totalAmount(req.getTotalAmount())
                .status(OrderStatus.PENDING)
                .build();
    }

    @Override
    public OrderResponse toResponse(@NonNull Order entity) {
        return OrderResponse.builder()
                .items(entity.getItems())
                .customer(customerMapper.toResponse(entity.getCustomer()))
                .orderRemarks(entity.getOrderRemarks())
                .orderType(entity.getOrderType())
                .totalAmount(entity.getTotalAmount())
                .id(entity.getId())
                .status(entity.getStatus() == null ? OrderStatus.PENDING : entity.getStatus())
                .build();
    }

}
