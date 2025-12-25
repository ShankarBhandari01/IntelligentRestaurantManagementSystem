package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<Void> processOrder(OrderRequest orderRequest);
}
