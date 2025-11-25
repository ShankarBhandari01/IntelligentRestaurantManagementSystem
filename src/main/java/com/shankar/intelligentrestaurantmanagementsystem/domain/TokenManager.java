package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenManager {
    private final TokenService tokenService;


    public Token saveTokenToDatabase(Object token, User user, String type, Object ExpiresAt) {
        // SAVE token to DB
        Token newToken = new Token();
        newToken.setUser(user);
        newToken.setToken(token.toString());
        newToken.setType(type);
        newToken.setExpired(false);
        newToken.setRevoked(false);
        newToken.setExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(ExpiresAt.toString())));
        tokenService.saveToken(newToken);
        return newToken;
    }



}
