package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.UserRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.UserResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signUp")
    public ResponseEntity<@NonNull ApiResponse<?>> create(@RequestBody @Validated UserRequest request) {
        try {
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body(new ApiResponse<>(true, "Email already in use", null));
            }

            // Hash the password
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            // Create user
            return ResponseEntity.ok(new ApiResponse<>(true, "User Created", userService.createUser(request)));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<@NonNull UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}

