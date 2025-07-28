package com.schh.blogapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationPeriod;

    public String generateToken(Authentication authentication) {
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationPeriod);
        return Jwts.builder()
                .subject(authentication.getName())
                .expiration(expiryDate)
                .signWith(key())
                .compact();
    }

    private Key key() {
        byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);
        return Keys.hmacShaKeyFor(decodedSecret);
    }
}
