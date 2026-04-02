package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import com.shankar.intelligentrestaurantmanagementsystem.repository.TokenRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Async
    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    @Async
    @Override
    public CompletableFuture<Token> getToken(String token) {
        return CompletableFuture.completedFuture(tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found")));
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

    @Async
    @Override
    public CompletableFuture<List<Token>> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId) {
        return CompletableFuture.completedFuture(tokenRepository.findAllByUserIdAndExpiredFalseAndRevokedFalse(userId)
                .orElseThrow(() -> new RuntimeException("Token not found")));
    }
}
