package com.loginwithjwt.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secretKey = "fwfefewfewfgewgfewgewgewgewFGFR54HT4J5JKYTHNFGHJKKYTKYTJYTJJYTJYTJYTFGHNTRH";

//    public String generateToken(String username){
//        Date now  = new Date();
//        Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
//        Claims claims = Jwts.claims().setSubject(username);
//        String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(SignatureAlgorithm.RS256,Ser)
//
//    }
}
