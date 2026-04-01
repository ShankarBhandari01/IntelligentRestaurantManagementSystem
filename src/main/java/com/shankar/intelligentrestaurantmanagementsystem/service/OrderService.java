package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<OrderResponse> processOrder(OrderRequest orderRequest);

    CompletableFuture<OrderResponse> getOrderById(Long id);

    CompletableFuture<List<OrderResponse>> getAllOrders();

    CompletableFuture<OrderResponse> updateOrder(Long id, OrderRequest request);

}
