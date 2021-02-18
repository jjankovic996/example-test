package org.example.clean.architecture.repository;

import org.example.clean.architecture.UserBM;

public interface UserRepository {

    UserBM findUserByUsername(String username);

    Boolean doesUserExistByUsername(String username);

    Boolean doesUserExistByEmail(String email);

    UserBM  save(UserBM userBM);
}
