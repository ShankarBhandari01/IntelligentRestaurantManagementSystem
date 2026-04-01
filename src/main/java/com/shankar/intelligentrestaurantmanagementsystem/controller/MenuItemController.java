package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.MenuItemResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

// need better logging monitoring and error handling
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/menuItem")
public class MenuItemController extends BaseController {
    private final MenuItemService menuItemService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<@NonNull ApiResponse<MenuItemResponse>> addMenuItem(
            @RequestBody
            @Valid MenuItemRequest menuItemRequest)
            throws ExecutionException, InterruptedException {

        var response = menuItemService.saveMenuItem(menuItemRequest).get();
        return created(response);
    }

    // add pagination to this api later
    @GetMapping("/all")
    public ResponseEntity<@NonNull ApiResponse<List<MenuItemResponse>>> getAllMenuItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws ExecutionException, InterruptedException {

        var response = menuItemService.getAllMenuItems().get();
        return ok(response);
    }
}

