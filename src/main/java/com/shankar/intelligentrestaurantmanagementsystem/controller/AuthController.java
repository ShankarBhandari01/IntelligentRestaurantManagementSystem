package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.LoginRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.LoginResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.service.TokenService;
import com.shankar.intelligentrestaurantmanagementsystem.util.CustomUserDetails;
import com.shankar.intelligentrestaurantmanagementsystem.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<@NonNull ApiResponse<?>> login(@RequestBody @Valid LoginRequest request) {
        try {
            // Authenticate credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );

            // Get authenticated user details
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            LoginResponse response;

            if (userDetails != null) {
                response = prepareLoginResponse(userDetails.getUser());
            } else {
                throw new RuntimeException("User not found");
            }

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Login Successful", response)
            );

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "Authentication failed", null));
        }
    }

    private LoginResponse prepareLoginResponse(User user) {
        // Generate JWT with claims
        String token = jwtUtil.generateToken(user);

        // SAVE token to DB
        Token newToken = new Token();
        newToken.setUserId(user.getId());
        newToken.setToken(token);
        newToken.setExpired(false);
        newToken.setRevoked(false);
        newToken.setExpiresAt(new Date(System.currentTimeMillis() + jwtUtil.JWT_EXPIRATION));

        tokenService.saveToken(newToken);

        return LoginResponse.builder()
                .token(token)
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .isEnabled(user.getIsEnabled())
                .build();
    }
}

