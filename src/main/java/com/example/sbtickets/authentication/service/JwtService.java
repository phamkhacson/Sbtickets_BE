package com.example.sbtickets.authentication.service;

import java.util.Date;

import com.example.sbtickets.entity.User;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
// JwtService dùng để tạo và validate Token

@Component
@Slf4j
public class JwtService {
    private static final Logger logger = LogManager.getLogger(JwtService.class);
    // Đoạn JWT_SECRET này là bảo mật, chỉ có phía server biết
    private final String JWT_SECRET = "JWT_SECRET_1584547915594";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 288000000;

    // Tạo ra jwt từ thông tin user
    public String generateToken(User userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(userDetails.getUserName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        if (authToken == null || authToken.trim().length() == 0) {
            return false;
        }
        String username = getUserIdFromJWT(authToken);
        if (username == null || username.isEmpty()) {
            return false;
        }
        return true;
    }
}

