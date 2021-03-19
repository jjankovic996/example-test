package org.example.clean.architecture.usecase;

import org.example.clean.architecture.User;
import org.example.clean.architecture.exception.RegisterException;


public interface RegisterUserCase {
    User register(User user) throws RegisterException;
}
