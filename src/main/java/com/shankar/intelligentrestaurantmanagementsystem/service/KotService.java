package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;

import java.util.concurrent.CompletableFuture;

public interface KotService {
    CompletableFuture<Kot> sendOrderToKot(Kot kot);

    CompletableFuture<Void> fallback(Kot kot, Throwable t);
}
