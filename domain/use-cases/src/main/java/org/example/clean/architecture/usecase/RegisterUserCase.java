package org.example.clean.architecture.usecase;

import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.exception.RegisterException;


public interface RegisterUserCase {
    UserBM register(UserBM userBM) throws RegisterException;
}
