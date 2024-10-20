package com.example.fuelmanagement.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {


    private String jwtSecret = "23423rwfsdf7fs7tv8xvyx8cvxt565g5g67yc8vyxc8vfsdfyxc8vyhxc7vyxcvyxc7vyxcyvxc89yv";
    private int jwtExpirationMs = 3600000;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
