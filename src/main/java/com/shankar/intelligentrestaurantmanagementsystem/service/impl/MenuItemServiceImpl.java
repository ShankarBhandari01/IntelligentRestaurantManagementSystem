package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.domain.MenuItemValidation;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.MenuItemResponse;
import com.shankar.intelligentrestaurantmanagementsystem.exception.ApiException;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.MenuItemMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.CategoryRepository;
import com.shankar.intelligentrestaurantmanagementsystem.repository.MenuItemRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// add cache to this service
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemMapper menuItemMapper;

    @Async
    @Override
    public CompletableFuture<MenuItemResponse> saveMenuItem(MenuItemRequest request) {
        try {
            // validate the menu item
            MenuItemValidation validation = new MenuItemValidation(request);
            validation.validateMenuItem();
            // map the request to entity
            var menuItemEntity = menuItemMapper.toEntity(request);

            if (menuItemRepository.existsByItemNameEnIgnoreCaseAndCategoryId(
                    request.getItemName().getEn(),
                    request.getCategoryId())) {
                throw new ApiException("Duplicate menu item");
            }

            var category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

            menuItemEntity.setCategory(category); // set the category
            var savedItem = menuItemRepository.save(menuItemEntity);

            return CompletableFuture.completedFuture(menuItemMapper.toResponse(savedItem));
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<MenuItemResponse> getMenuItem(String id) {
        var menuItem = menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu Item not found"));
        return CompletableFuture.completedFuture(menuItemMapper.toResponse(menuItem));
    }

    @Async
    @Override
    public CompletableFuture<List<MenuItemResponse>> getAllMenuItems() {
        List<MenuItemResponse> menuItems = menuItemRepository.findAll().stream().map(menuItemMapper::toResponse).toList();
        return CompletableFuture.completedFuture(menuItems);
    }

}
