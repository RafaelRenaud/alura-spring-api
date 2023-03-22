package com.br.med.voll.api.service;

import com.br.med.voll.api.model.dto.user.User;

public interface TokenService {
    String generateToken(User user);
    String getTokenSubject(String token);
}
