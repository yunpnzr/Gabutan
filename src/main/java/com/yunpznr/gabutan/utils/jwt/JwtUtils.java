package com.yunpznr.gabutan.utils.jwt;

import com.yunpznr.gabutan.configuration.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@AllArgsConstructor
@Component
public class JwtUtils {
    private final JwtConfig config;

    //buat ambil body (Claims) dari JWS
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(config.getSecret().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //buat generate token
    public String generateToken(String username) {
        Key key = Keys.hmacShaKeyFor(config.getSecret().getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpiration()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //ambil username
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    //validasi token
    public boolean validateToken(String token) {
        try{
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //cek expired token
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
