package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.domain.UserRolesManager;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.UserRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.UserResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.exception.ResourceNotFoundException;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.UserMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.UserRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRolesManager userRolesManager;

    @Async
    @Override
    public CompletableFuture<UserResponse> createUser(UserRequest request) {
        try {

            User user = userMapper.toEntity(request);
            // assign roles to user
            user = userRolesManager.addRoleToUser(user, request.getRoles());
            // user to database
            userRepository.save(user);
            // return user
            return CompletableFuture.completedFuture(userMapper.toResponse(user));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Async
    @Override
    public CompletableFuture<UserResponse> getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return CompletableFuture.completedFuture(userMapper.toResponse(user));
    }

    @Async
    @Override
    public CompletableFuture<UserResponse> getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return CompletableFuture.completedFuture(userMapper.toResponse(user));
    }

    @Async
    @Override
    public CompletableFuture<Boolean> existsByEmail(String email) {
        return CompletableFuture.completedFuture(userRepository.existsByEmail(email));
    }


}
