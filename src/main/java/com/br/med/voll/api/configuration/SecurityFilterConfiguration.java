package com.br.med.voll.api.configuration;

import com.br.med.voll.api.repository.UserRepository;
import com.br.med.voll.api.service.impl.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class SecurityFilterConfiguration extends OncePerRequestFilter {

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = getToken(request);

        if(bearerToken != null){
            String login = tokenService.getTokenSubject(bearerToken);
            UserDetails user = userRepository.findByLogin(login);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if(authorization != null){
            return authorization.replace("Bearer ","");
        }

        return null;
    }
}
