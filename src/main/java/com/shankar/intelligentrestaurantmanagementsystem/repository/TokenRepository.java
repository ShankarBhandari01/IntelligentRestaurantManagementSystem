package com.shankar.intelligentrestaurantmanagementsystem.repository;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Token;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<@NonNull Token, @NonNull Long> {

    @Query("SELECT t FROM Token t WHERE t.token = ?1")
    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t WHERE t.userId = ?1 AND t.expired = false AND t.revoked = false")
    Optional<List<Token>> findAllByUserIdAndExpiredFalseAndRevokedFalse(Long userId);
}
