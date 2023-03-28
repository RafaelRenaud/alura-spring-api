package com.br.med.voll.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.med.voll.api.model.entity.User;
import com.br.med.voll.api.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${server.security.tokenSecret}")
    private String tokenSecret;

    @Override
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenSecret);
            return JWT.create()
                    .withIssuer("voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    @Override
    public String getTokenSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenSecret);
            return JWT.require(algorithm)
                    .withIssuer("voll.med")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid JWT Token");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
