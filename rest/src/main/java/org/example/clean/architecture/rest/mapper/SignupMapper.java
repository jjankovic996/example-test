package org.example.clean.architecture.rest.mapper;

import org.example.clean.architecture.Role;
import org.example.clean.architecture.RoleType;
import org.example.clean.architecture.User;
import org.example.clean.architecture.rest.request.SignupRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SignupMapper {

    public User toUser(SignupRequest signupRequest){
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(),signupRequest.getPassword());
        if(signupRequest.getRoles() != null && signupRequest.getRoles().size() > 0){
            user.setRoles(toRole(signupRequest.getRoles()));
        }

        return  user;
    }

    private Set<Role> toRole(Set<String> rolesRequest){
        Set<Role> roles = new HashSet<>(rolesRequest.size());
        for (String roleRequest : rolesRequest){
            Role role = new Role(roleRequest.equals(RoleType.ROLE_USER.name())? RoleType.ROLE_USER : RoleType.ROLE_ADMIN);
            roles.add(role);
        }

        return roles;
    }
}
