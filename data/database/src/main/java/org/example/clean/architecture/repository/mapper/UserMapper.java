package org.example.clean.architecture.repository.mapper;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.RoleTypeBM;
import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.repository.model.Role;
import org.example.clean.architecture.repository.model.RoleType;
import org.example.clean.architecture.repository.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {
    public UserBM  toUserBM(User user){
        UserBM  userBM = new UserBM(user.getEmail(), user.getUsername(), user.getPassword());
        userBM.setId(user.getId());

        if(user.getRoles().size()>0) {
            Set<RoleBM> roleBMs = new HashSet<>();
            user.getRoles().forEach(role -> roleBMs.add(toRoleBM(role)));
            userBM.setRoles(roleBMs);
        }

        return userBM;
    }

    private RoleBM toRoleBM(Role role){
        if(role.getName().equals(RoleType.ROLE_ADMIN))
            return new RoleBM(RoleTypeBM.ROLE_ADMIN);
        else
            return new RoleBM(RoleTypeBM.ROLE_USER);
    }

    public User toUser(UserBM userBM){
        User user = new User(userBM.getEmail(), userBM.getUsername(), userBM.getPassword());

        if(userBM.getRoles().size()>0) {
            Set<Role> roles = new HashSet<>();
            userBM.getRoles().forEach(roleBM -> roles.add(toRole(roleBM)));
            user.setRoles(roles);
        }
        return user;
    }

    private Role toRole(RoleBM roleBM){
        if(roleBM.getName().equals(RoleTypeBM.ROLE_ADMIN))
            return new Role(RoleType.ROLE_ADMIN);
        else
            return new Role(RoleType.ROLE_USER);
    }
}
