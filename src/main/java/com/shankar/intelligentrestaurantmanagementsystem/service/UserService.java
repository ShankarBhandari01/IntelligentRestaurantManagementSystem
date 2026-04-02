package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.UserRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.UserResponse;

import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<UserResponse> createUser(UserRequest request);

    CompletableFuture<UserResponse> getUser(Long id);

    CompletableFuture<UserResponse> getUserByEmail(String username);
    CompletableFuture<Boolean> existsByEmail(String email);
}
