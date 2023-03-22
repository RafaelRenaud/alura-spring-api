package com.br.med.voll.api.service.impl;

import com.br.med.voll.api.model.dto.authentication.AuthenticationResponseDTO;
import com.br.med.voll.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public AuthenticationResponseDTO fetchAuthenticationObject(String token){
        return new AuthenticationResponseDTO(
                OffsetDateTime.now().toString(),
                UUID.randomUUID().toString(),
                token
        );
    }
}
