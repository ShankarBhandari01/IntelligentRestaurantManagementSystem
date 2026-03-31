package com.shankar.intelligentrestaurantmanagementsystem.dto.response;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Set<Role> role;
    private Boolean isEnabled;
}
