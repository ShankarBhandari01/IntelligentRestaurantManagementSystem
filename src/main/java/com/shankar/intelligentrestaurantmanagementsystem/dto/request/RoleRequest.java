package com.shankar.intelligentrestaurantmanagementsystem.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class RoleRequest {
    @NonNull
    private String name;
}
