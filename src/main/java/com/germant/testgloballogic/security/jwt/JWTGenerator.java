package com.germant.testgloballogic.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Component
public class JWTGenerator {


    @Value("${jwtExpirationMs}")
    private long jwtExpirationMs;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken, UserDetails userDetails) {
        String username = getUserNameFromJwtToken(authToken);
        if(username.equals(userDetails.getUsername()) && expiredToken(authToken)) {
            return true;
        }

        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",e.fillInStackTrace());
        }
    }

    private boolean expiredToken(String authToken) {
        return getExpirate(authToken).before(new Date());
    }

    private Date getExpirate(String authToken) {
        return extractClaim(authToken,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String authToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken).getBody();
    }

    public String generateToken(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

}
