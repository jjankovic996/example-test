package org.example.clean.architecture.repository;

import org.example.clean.architecture.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(Integer id);
}
