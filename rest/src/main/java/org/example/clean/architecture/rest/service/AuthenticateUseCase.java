package org.example.clean.architecture.rest.service;

import org.example.clean.architecture.JwtToken;

public interface AuthenticateUseCase {
    JwtToken authenticate(String email, String password);
}
