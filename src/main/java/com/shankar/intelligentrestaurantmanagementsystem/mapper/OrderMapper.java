package com.shankar.intelligentrestaurantmanagementsystem.mapper;


import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequest req) {
        return Order.builder()
                .items(req.getItems())
                .orderRemarks(req.getOrderRemarks())
                .orderType(req.getOrderType())
                .totalAmount(req.getTotalAmount())
                .build();
    }
}
