package com.schh.blogapi.security;

import com.schh.blogapi.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
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

    public String getUserName(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "The jwt is expired");
        } catch (MalformedJwtException e) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "The jwt is invalid");
        } catch (SecurityException e) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "The token signature is invalid");
        } catch (UnsupportedJwtException e) {
          throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Jwt claims string is empty");
        }
    }
}
