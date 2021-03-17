package org.example.clean.architecture.rest.mapper;

import org.example.clean.architecture.JwtToken;
import org.example.clean.architecture.rest.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {
    public LoginResponse toLoginResponse(JwtToken jwtToken){
        return new LoginResponse(jwtToken.getAccessToken(),
                jwtToken.getUsername(),
                jwtToken.getEmail(),
                jwtToken.getRoles());
    }
}
