package com.shankar.intelligentrestaurantmanagementsystem.util;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    // Secret key
    private final String SECRET_KEY = "your_secret_key_here_change_this_to_very_long_key";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public final long JWT_EXPIRATION = 1 * 60 * 60 * 1000; // 1 hours
    public final long JWT_REFRESH_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 days

    // Generate token
    public Map<String, Object> generateTokens(User user) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        // regular token and refresh token
        response.put("token", generateToken(claims, user, "token"));
        response.put("tokenExpiration", JWT_EXPIRATION);

        // and refresh token
        response.put("refresh_token", generateToken(claims, user, "Refresh"));
        response.put("refresh_tokenExpiration", JWT_REFRESH_EXPIRATION);

        return response;
    }

    private String generateToken(Map<String, Object> claims, User user, String tokenType) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (Objects.equals(tokenType, "token") ? JWT_EXPIRATION : JWT_REFRESH_EXPIRATION)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }

    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> {
            Object rolesObject = claims.get("roles");

            if (rolesObject instanceof List<?>) {
                return ((List<?>) rolesObject)
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());
            }

            return Collections.emptyList();
        });
    }

    // Extract expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic claim extractor
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractEmail(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)        // parserBuilder instead of parser()
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
