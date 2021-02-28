package org.example.clean.architecture.rest.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String accessToken;
    private String type;
    private String username;
    private String email;
    private List<String> roles;

    public LoginResponse(String accessToken, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.type = "Bearer";
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}

