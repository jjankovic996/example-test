package org.example.clean.architecture.repository.jpa_repository;

import org.example.clean.architecture.repository.model.Role;
import org.example.clean.architecture.repository.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoryJPA extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);
}
