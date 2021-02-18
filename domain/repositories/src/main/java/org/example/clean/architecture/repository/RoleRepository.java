package org.example.clean.architecture.repository;

import org.example.clean.architecture.RoleBM;

public interface RoleRepository {
    RoleBM findByName(String name);

    RoleBM update(RoleBM roleBm);
}
