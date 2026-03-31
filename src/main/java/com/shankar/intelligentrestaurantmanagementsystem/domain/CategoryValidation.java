package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.CategoryRequest;

public class CategoryValidation {
    private final CategoryRequest Category;

    public CategoryValidation(CategoryRequest Category) {
        this.Category = Category;
    }

    public void doValidate() {
        validateCategoryName();
    }

    private void validateCategoryName() {

    }
}
