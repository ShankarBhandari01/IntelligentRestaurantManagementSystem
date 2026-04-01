package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.UserRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.UserResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserRequest, UserResponse> {
    @Override
    public User toEntity(@NonNull UserRequest req) {
        return User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .isEnabled(req.getIsEnabled())
                .build();
    }

    @Override
    public UserResponse toResponse(@NonNull User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isEnabled(user.getIsEnabled())
                .role(user.getRoles())
                .build();
    }
}
