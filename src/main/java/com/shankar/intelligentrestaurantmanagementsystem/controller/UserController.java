package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.UserRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.UserResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/sign-up")
    public ResponseEntity<@NonNull ApiResponse<?>> create(@RequestBody @Valid UserRequest request) {
        try {
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body(new ApiResponse<>(true, "Email already in use", null));
            }

            // Hash the password
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            assert hashedPassword != null;
            request.setPassword(hashedPassword);

            // save user
            var response = userService.createUser(request);

            // Create user
            return ResponseEntity.ok(new ApiResponse<>(true, "User Created", response));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<@NonNull UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}

