package com.shankar.intelligentrestaurantmanagementsystem.dto.request;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class UserRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Boolean isEnabled;

    private List<Role> roles;
}
