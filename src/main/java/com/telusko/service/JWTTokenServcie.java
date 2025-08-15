package com.telusko.service;

import com.telusko.model.UserPrincipal;
import com.telusko.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenServcie {
    private String key;

    JWTTokenServcie() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        key = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
    }

    public String generateJWTToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("role", "USER");
        // Here you would typically use a JWT library to create a token with the claims
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .subject(user.getUsername())
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key); // Replace with your secret key
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public UserPrincipal validateToken(String jwtToken) {
        try {
            String username = Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody()
                    .getSubject();

            // Here you would typically load the user from the database using the username
            Users user = new Users(); // Replace with actual user retrieval logic
            user.setUsername(username);
            return new UserPrincipal(user);
        } catch (Exception e) {
            // Handle token validation failure
            return null;
        }
    }
}
