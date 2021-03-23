package org.example.clean.architecture.rest.service;

import org.example.clean.architecture.JwtToken;
import org.example.clean.architecture.rest.security.jwt.JwtUtils;
import org.example.clean.architecture.rest.security.services.UserDetailsImpl;
import org.example.clean.architecture.usecase.AuthenticateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticateService implements AuthenticateUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @Autowired
    public AuthenticateService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public JwtToken authenticate(String email, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority item : userDetails.getAuthorities()) {
            String authority = item.getAuthority();
            roles.add(authority);
        }

        return new JwtToken(jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
