package com.minhaacademiaonline.api.application.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;
import java.util.function.Function;

public interface ITokenService {
    String generateToken(UUID tenantId, UUID sub, String subdomain, String userName);
    String extractUserName(String token);

    <T> T extractClaims(String token, io.jsonwebtoken.impl.lang.Function<Claims, T> claimsResolver);

    boolean isTokenValid(String token, UserDetails userDetails);
}
