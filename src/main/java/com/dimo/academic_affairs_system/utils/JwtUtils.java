package com.dimo.academic_affairs_system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
@Component
public class JwtUtils {
    private static final SecretKey key = Keys.hmacShaKeyFor("Dimos-personal-key-is-this-one-and-with-at-least-32-bits-long".getBytes(StandardCharsets.UTF_8));
    private static Long expire = 3600L;

    public static String generateJwt(Map<String, Object> claims){

        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(key)
                .expiration(Date.from(Instant.now().plusSeconds(expire)))
                .compact();
        return jwt;
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }
}
