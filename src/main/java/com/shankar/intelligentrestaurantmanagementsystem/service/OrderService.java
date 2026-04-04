package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderFilterRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.PaginatedResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<OrderResponse> processOrder(OrderRequest orderRequest);

    CompletableFuture<OrderResponse> getOrderById(Long id);

    CompletableFuture<PaginatedResponse<OrderResponse>> getAllOrders(OrderFilterRequest filter, int page, int size);

    CompletableFuture<OrderResponse> updateOrder(Long id, OrderRequest request);

}
