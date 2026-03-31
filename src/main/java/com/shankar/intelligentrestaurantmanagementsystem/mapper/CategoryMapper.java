package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.CategoryRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.CategoryResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper {

    public Category toEntity(CategoryRequest request) {
        return Category.builder()
                .name(mapToLanguage(request.getName()))
                .description(mapToLanguage(request.getDescription()))
                .remarks(mapToLanguage(request.getRemarks()))
                .isLunchCategory(request.getIsLunchCategory())
                .isOrderable(request.getIsOrderable())
                .isActive(request.getIsActive())
                .isDeleted(request.getIsDeleted())
                .build();

    }


    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(mapToLanguageDto(category.getName()))
                .description(mapToLanguageDto(category.getDescription()))
                .remarks(mapToLanguageDto(category.getRemarks()))
                .isLunchCategory(category.getIsLunchCategory())
                .isOrderable(category.getIsOrderable())
                .isActive(category.getIsActive())
                .isDeleted(category.getIsDeleted())
                .build();
    }
}
