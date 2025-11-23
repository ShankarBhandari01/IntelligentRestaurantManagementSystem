package com.shankar.intelligentrestaurantmanagementsystem.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;

}
