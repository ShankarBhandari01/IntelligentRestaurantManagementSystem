package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;

import java.util.List;


public interface TokenService {
    void saveToken(Token token);

    Token getToken(String token);

    void deleteToken(String token);

    void deleteAllExpiredTokens();

    void deleteAllRevokedTokens();

    void revokeToken(String token);

    void revokeAllTokens(Long userId);

    boolean validateToken(String token, Long userId);

    List<Token> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId);

}
