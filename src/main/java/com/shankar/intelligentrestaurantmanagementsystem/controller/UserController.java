package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.UserRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.UserResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<@NonNull ApiResponse<UserResponse>> create(@RequestBody @Valid UserRequest request, HttpSession session) throws ExecutionException, InterruptedException {

        if (userService.existsByEmail(request.getEmail()).get()) {
            return fail("Email already in use");
        }
        // Hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        assert hashedPassword != null;
        request.setPassword(hashedPassword);

        // save user
        var response = userService.createUser(request).get();

        // Create user
        return created(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<@NonNull ApiResponse<UserResponse>> get(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return ok(userService.getUser(id).get());
    }
}

