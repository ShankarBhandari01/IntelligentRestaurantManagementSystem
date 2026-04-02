package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.domain.TokenManager;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.LoginRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.LoginResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.util.CustomUserDetails;
import com.shankar.intelligentrestaurantmanagementsystem.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
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

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<@NonNull ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request,
                                                                     HttpSession session) {
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
            return ok(response, "Login Successful");

        } catch (BadCredentialsException e) {
            return fail("Invalid email or password");
        } catch (AuthenticationException e) {
            return fail("Authentication failed");
        }
    }

    private LoginResponse prepareLoginResponse(User user) {
        try {
            Map<String, Object> token = jwtUtil.generateTokens(user);

            Token newToken = tokenManager.saveTokenToDatabase(token.get("token"), user, "token", token.get("tokenExpiration")).get();
            Token refreshToken = tokenManager.saveTokenToDatabase(token.get("refresh_token").toString(),
                    user, "refresh", token.get("refresh_tokenExpiration")).get();

            return LoginResponse.builder()
                    .token(newToken.getToken())
                    .refreshToken(refreshToken.getToken())
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .roles(user.getRoles())
                    .isEnabled(user.getIsEnabled())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

