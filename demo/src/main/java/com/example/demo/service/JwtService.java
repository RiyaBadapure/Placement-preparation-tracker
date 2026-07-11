package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "MySecretKeyForJWTAuthentication123456789";

    private SecretKey getSigningKey()
    {
        return Keys.hmacShaKeyFor(
            SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String username)
    {
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000*60*60))
            .signWith(getSigningKey())
            .compact();

    }

    public String extractUsername(String token)
    {
        return Jwts.parser()
             .verifyWith(getSigningKey())
             .build()
             .parseSignedClaims(token)
             .getPayload()
             .getSubject();
             
    }

    public Date extractExpiration(String token)
    {
        return Jwts.parser()
              .verifyWith(getSigningKey())
              .build()
              .parseSignedClaims(token)
              .getPayload()
              .getExpiration();
    }
    
    public Boolean validateToken(String token)
    {
        Date expiration=extractExpiration(token);

        return expiration.after(new Date());

        
       
    }

    public boolean validateToken(String token, UserDetails userDetails)
{
    String username = extractUsername(token);

    return username.equals(userDetails.getUsername())
            && extractExpiration(token).after(new Date());
}
    
}
