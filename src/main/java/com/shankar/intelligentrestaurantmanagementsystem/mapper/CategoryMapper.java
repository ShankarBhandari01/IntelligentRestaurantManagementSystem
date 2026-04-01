package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.CategoryRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.CategoryResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Category;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<Category, CategoryRequest, CategoryResponse> {
    private final LanguageMapper languageMapper;

    @Override
    public Category toEntity(@NonNull CategoryRequest request) {

        return Category.builder()
                .name(languageMapper.toEntity(request.getName()))
                .description(languageMapper.toEntity(request.getDescription()))
                .remarks(languageMapper.toEntity(request.getRemarks()))
                .isLunchCategory(request.getIsLunchCategory())
                .isOrderable(request.getIsOrderable())
                .isActive(request.getIsActive())
                .isDeleted(request.getIsDeleted())
                .build();

    }

    @Override
    public CategoryResponse toResponse(@NonNull Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(languageMapper.toResponse(category.getName()))
                .description(languageMapper.toResponse(category.getDescription()))
                .remarks(languageMapper.toResponse(category.getRemarks()))
                .isLunchCategory(category.getIsLunchCategory())
                .isOrderable(category.getIsOrderable())
                .isActive(category.getIsActive())
                .isDeleted(category.getIsDeleted())
                .build();
    }
}
