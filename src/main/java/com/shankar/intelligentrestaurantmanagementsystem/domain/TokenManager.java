package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.service.TokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class TokenManager {
    private final TokenService tokenService;

    @Async
    public CompletableFuture<Token> saveTokenToDatabase(@NonNull Object token, User user, String type, @NonNull Object ExpiresAt) {
        // SAVE token to DB
        Token newToken = new Token();
        newToken.setUser(user);
        newToken.setToken(token.toString());
        newToken.setType(type);
        newToken.setExpired(false);
        newToken.setRevoked(false);
        newToken.setExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(ExpiresAt.toString())));
        tokenService.saveToken(newToken);
        return CompletableFuture.completedFuture(newToken);
    }



}
