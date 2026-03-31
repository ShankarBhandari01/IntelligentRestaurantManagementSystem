package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.CategoryRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.CategoryResponse;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.CategoryMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.CategoryRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CompletableFuture<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        try {
            var category = categoryMapper.toEntity(categoryRequest);
            category = categoryRepository.save(category);
            return CompletableFuture.completedFuture(categoryMapper.toResponse(category));

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<List<CategoryResponse>> getAllCategories() {
        try {

            List<CategoryResponse> categories = categoryRepository
                    .findAll()
                    .stream()
                    .map(categoryMapper::toResponse)
                    .toList();

            return CompletableFuture.completedFuture(categories);

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
