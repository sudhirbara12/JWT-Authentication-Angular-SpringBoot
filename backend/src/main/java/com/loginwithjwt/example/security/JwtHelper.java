package com.loginwithjwt.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

/**
 * Utility class for handling jwt security tokens
 *
 * @author Sudhir Bara
 */
@Component
public class JwtHelper {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;


    private static final String secretKey = "fwfefewfewfgewgfewgewgewgewFGFR54HT4J5JKYTHNFGHJKKYTKYTJYTJJYTJYTJYTFGHNTRH";

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
        Claims claims = Jwts.claims().setSubject(username);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(getSigningKey()).compact();
    }

    public boolean validateToken(String token , UserDetails userDetails) {
        try {
            Claims claims = extractAllClaims(token);
            return userDetails.getUsername().equalsIgnoreCase(claims.getSubject()) || !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    public String getUserNameFromToken(String token){
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }
}
