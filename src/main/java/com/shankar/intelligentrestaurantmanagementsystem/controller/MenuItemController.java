package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

// need better logging monitoring and error handling
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/menuItem")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<@NonNull ApiResponse<?>> addMenuItem(
            @RequestBody
            @Valid MenuItemRequest menuItemRequest) {
        try {
            var response = menuItemService.saveMenuItem(menuItemRequest).get();
            return ResponseEntity.status(201).body(new ApiResponse<>(true, "Menu Item added successfully", response));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getCause().getMessage(), null));
        }
    }

    // add pagination to this api later

    @GetMapping("/all")
    public ResponseEntity<@NonNull ApiResponse<?>> getAllMenuItems() {
        try {
            var response = menuItemService.getAllMenuItems().get();

            return ResponseEntity.ok(new ApiResponse<>(true, "", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

}
