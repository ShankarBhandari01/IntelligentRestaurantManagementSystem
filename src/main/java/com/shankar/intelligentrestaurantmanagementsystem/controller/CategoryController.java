package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.CategoryRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.CategoryResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.CategoryService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<@NonNull ApiResponse<CategoryResponse>> addCategory(
            @RequestBody @Valid CategoryRequest categoryRequest
    ) {
        try {
            var response = categoryService.createCategory(categoryRequest).get();
            return created(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<@NonNull ApiResponse<List<CategoryResponse>>> getAllCategory() {
        try {
            var response = categoryService.getAllCategories().get();
            return ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
