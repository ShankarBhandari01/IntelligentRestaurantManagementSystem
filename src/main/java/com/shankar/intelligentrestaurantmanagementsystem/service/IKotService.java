package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IKotService {
    CompletableFuture<Kot>  sendOrderToKot(Kot kot);

    void saveKotItems(List<KotItem> items);

}
