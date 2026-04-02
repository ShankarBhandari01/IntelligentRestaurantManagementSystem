package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface TokenService {
    void saveToken(Token token);

    CompletableFuture<Token> getToken(String token);

    void deleteToken(String token);

    void deleteAllExpiredTokens();

    void deleteAllRevokedTokens();

    void revokeToken(String token);

    void revokeAllTokens(Long userId);

    boolean validateToken(String token, Long userId);

    CompletableFuture<List<Token>> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId);

}
