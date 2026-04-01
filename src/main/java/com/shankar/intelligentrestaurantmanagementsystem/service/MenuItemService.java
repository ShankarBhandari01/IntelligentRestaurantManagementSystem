package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.MenuItemResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MenuItemService {
    CompletableFuture<MenuItemResponse> saveMenuItem(MenuItemRequest request);

    CompletableFuture<MenuItemResponse> getMenuItem(String id);

    CompletableFuture<List<MenuItemResponse>> getAllMenuItems();

}
