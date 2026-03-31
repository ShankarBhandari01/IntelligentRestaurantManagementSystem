package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.repository.KotRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.KotService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KotServiceImpl implements KotService {
    private final KotRepository kotRepository;

    @Async
    @Override
   // @CircuitBreaker(name = "kotService", fallbackMethod = "fallback")
    @Retry(name = "kotServiceRetry")
    public CompletableFuture<Kot> sendOrderToKot(Kot kot) {
        var savedKot = kotRepository.save(kot);
        return CompletableFuture.completedFuture(savedKot);
    }

    // Fallback must match the method signature
    public CompletableFuture<Void> fallback(Kot kot, Throwable t) {
        System.err.println("KOT send failed, will retry later: " + t.getMessage());

        return CompletableFuture.completedFuture(null);
    }

}

