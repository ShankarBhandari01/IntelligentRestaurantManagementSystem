package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;
import com.shankar.intelligentrestaurantmanagementsystem.repository.KotItemRepository;
import com.shankar.intelligentrestaurantmanagementsystem.repository.KotRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.IKotService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class IKotServiceImpl implements IKotService {
    private final KotItemRepository kotItemRepository;
    private final KotRepository kotRepository;

    @Async
    @Override
    @Transactional
    public CompletableFuture<Kot> sendOrderToKot(Kot kot) {
        try {
            var savedKot = kotRepository.save(kot);
            return CompletableFuture.completedFuture(savedKot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Async
    @Transactional
    @Override
    public void saveKotItems(List<KotItem> items) {
        try {
            kotItemRepository.saveAll(items);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

