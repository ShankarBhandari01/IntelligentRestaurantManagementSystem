package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.CategoryRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.CategoryResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CategoryService {
    CompletableFuture<CategoryResponse> createCategory(CategoryRequest categoryRequest);

    CompletableFuture<List<CategoryResponse>> getAllCategories();
}
