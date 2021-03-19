package org.example.clean.architecture.repository;

import org.example.clean.architecture.User;

public interface UserRepository {

    User findByUsername(String username);

    Integer countByUsername(String username);

    Integer countByEmail(String email);

    User  save(User user);
}
