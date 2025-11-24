package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import com.shankar.intelligentrestaurantmanagementsystem.repository.TokenRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public Token getToken(String token) {
        return tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found"));
    }

    @Override
    public void deleteToken(String token) {

    }

    @Override
    public void deleteAllExpiredTokens() {

    }

    @Override
    public void deleteAllRevokedTokens() {

    }

    @Override
    public void revokeToken(String token) {

    }

    @Override
    public void revokeAllTokens(Long userId) {

    }

    @Override
    public boolean validateToken(String token, Long userId) {
        return false;
    }

    @Override
    public List<Token> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId) {
        return tokenRepository.findAllByUserIdAndExpiredFalseAndRevokedFalse(userId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
