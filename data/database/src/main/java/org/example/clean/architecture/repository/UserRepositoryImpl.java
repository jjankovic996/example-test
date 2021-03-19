package org.example.clean.architecture.repository;

import org.example.clean.architecture.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends UserRepository, JpaRepository<User, Long> {

}
