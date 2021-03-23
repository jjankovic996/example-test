package org.example.clean.architecture.usecase;

import org.example.clean.architecture.JwtToken;

public interface AuthenticateUseCase {
    JwtToken authenticate(String email, String password);
}
