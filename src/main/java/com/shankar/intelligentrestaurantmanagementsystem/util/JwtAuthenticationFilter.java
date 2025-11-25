package com.shankar.intelligentrestaurantmanagementsystem.util;

import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.TokenService;
import com.shankar.intelligentrestaurantmanagementsystem.service.impl.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtUtil.extractEmail(token);
            }
            // Authenticate only if user not already authenticated
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // check if user exists in database
                var userDetails = userDetailsService.loadUserByUsername(username);

                // get token from database
                var dbToken = tokenService.getToken(token);

                if (dbToken != null && dbToken.isRevoked()) {
                    writeError(response, "Token Revoked");
                    return;
                } else if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                    var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Save authentication
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (ExpiredJwtException e) {
            writeError(response, "Token expired");
            return;
        } catch (JwtException | IllegalArgumentException e) {
            writeError(response, "Invalid Token");
            return;
        } catch (Exception e) {
            writeError(response, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);

    }


    public void writeError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(new ApiResponse<>(false, message, null)));
    }
}
