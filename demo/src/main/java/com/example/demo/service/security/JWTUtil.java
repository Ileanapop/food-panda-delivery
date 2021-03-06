package com.example.demo.service.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {

        String[] parts = username.split("-");
        String name = parts[0]; // 004
        String type = parts[1]; // 034556

        return JWT.create()
                .withSubject("UserAuth")
                .withIssuedAt(new Date())
                .withIssuer("FoodPandaApp")
                .withClaim("username", name)
                .withClaim("type", type)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("UserAuth")
                .withIssuer("FoodPandaApp")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

    public String retrieveUserType(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("UserAuth")
                .withIssuer("FoodPandaApp")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("type").asString();
    }

}
