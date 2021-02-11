package org.example.clean.architecture.usecase;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.exception.RegisterException;

import java.util.Set;

public interface RegisterUserCase {
    UserBM register(String username, String password, Set<RoleBM> role, String email) throws RegisterException;
}
