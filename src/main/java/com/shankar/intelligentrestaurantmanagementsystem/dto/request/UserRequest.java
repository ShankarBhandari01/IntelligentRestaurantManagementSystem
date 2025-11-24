package com.shankar.intelligentrestaurantmanagementsystem.dto.request;

import lombok.Data;
import lombok.NonNull;

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

    private long roleId;
}
