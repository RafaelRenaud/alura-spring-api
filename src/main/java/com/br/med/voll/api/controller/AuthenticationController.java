package com.br.med.voll.api.controller;

import com.br.med.voll.api.model.dto.authentication.AuthenticationRequestDTO;
import com.br.med.voll.api.model.dto.authentication.AuthenticationResponseDTO;
import com.br.med.voll.api.model.entity.User;
import com.br.med.voll.api.service.impl.AuthenticationServiceImpl;
import com.br.med.voll.api.service.impl.TokenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private TokenServiceImpl tokenService;

    @PostMapping
    public ResponseEntity login(
            @RequestBody
            @Valid
                    AuthenticationRequestDTO authenticationRequestDTO
    ){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.login(), authenticationRequestDTO.password());
        Authentication authentication = authenticationManager.authenticate(token);
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.fetchAuthenticationObject(
                tokenService.generateToken(
                        (User) authentication.getPrincipal()
                ));
        return ResponseEntity.ok(authenticationResponseDTO);
    }
}
