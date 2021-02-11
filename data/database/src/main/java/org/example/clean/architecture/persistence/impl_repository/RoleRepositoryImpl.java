package org.example.clean.architecture.persistence.impl_repository;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.persistence.jpa_repository.RoleRepositoryJPA;
import org.example.clean.architecture.persistence.mapper.RoleMapper;
import org.example.clean.architecture.persistence.model.Role;
import org.example.clean.architecture.persistence.model.RoleType;
import org.example.clean.architecture.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleRepositoryJPA roleRepositoryJPA;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleRepositoryImpl(RoleRepositoryJPA roleRepositoryJPA, RoleMapper roleMapper){
        this.roleRepositoryJPA = roleRepositoryJPA;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleBM findByName(String name) {
        RoleType roleType = name == RoleType.ROLE_ADMIN.name() ? RoleType.ROLE_ADMIN: RoleType.ROLE_USER;
        Role role = roleRepositoryJPA.findByName(roleType).get();

        return  roleMapper.toRoleBM(role);
    }
}
