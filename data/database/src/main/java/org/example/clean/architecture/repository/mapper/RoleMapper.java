package org.example.clean.architecture.repository.mapper;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.RoleTypeBM;
import org.example.clean.architecture.repository.model.Role;
import org.example.clean.architecture.repository.model.RoleType;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleBM toRoleBM(Role role){
        RoleBM roleBM = new RoleBM();

        switch (role.getName()){
            case ROLE_USER -> roleBM.setName(RoleTypeBM.ROLE_USER);
            case ROLE_ADMIN -> roleBM.setName(RoleTypeBM.ROLE_ADMIN);
            default -> throw new IllegalStateException("Unexpected value: " + role.getName());
        }

        return roleBM;
    }


    public Role toRole(RoleBM roleBM){
        Role role = new Role();

        switch (roleBM.getName()){
            case ROLE_USER -> role.setName(RoleType.ROLE_USER);
            case ROLE_ADMIN -> role.setName(RoleType.ROLE_ADMIN);
            default -> throw new IllegalStateException("Unexpected value: " + role.getName());
        }

        return role;
    }
}
