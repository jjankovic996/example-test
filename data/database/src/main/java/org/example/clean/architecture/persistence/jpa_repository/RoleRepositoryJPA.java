package org.example.clean.architecture.persistence.jpa_repository;

import org.example.clean.architecture.persistence.model.Role;
import org.example.clean.architecture.persistence.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoryJPA extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);
}
