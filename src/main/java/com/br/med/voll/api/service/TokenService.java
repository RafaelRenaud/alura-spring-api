package com.br.med.voll.api.service;

import com.br.med.voll.api.model.entity.User;

public interface TokenService {
    String generateToken(User user);
    String getTokenSubject(String token);
}
