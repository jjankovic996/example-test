package org.example.clean.architecture.repository;

import org.example.clean.architecture.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoryImpl extends RoleRepository, JpaRepository<Role, Integer> {
}
