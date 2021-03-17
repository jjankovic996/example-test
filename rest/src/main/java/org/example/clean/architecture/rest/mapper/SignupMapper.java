package org.example.clean.architecture.rest.mapper;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.RoleTypeBM;
import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.rest.request.SignupRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SignupMapper {

    public UserBM toUserBM(SignupRequest signupRequest){
        UserBM userBM = new UserBM(signupRequest.getUsername(), signupRequest.getEmail(),signupRequest.getPassword());
        if(signupRequest.getRole() != null && signupRequest.getRole().size() > 0){
            userBM.setRoles(toRoleBM(signupRequest.getRole()));
        }

        return  userBM;
    }

    private Set<RoleBM> toRoleBM(Set<String> roles){
        Set<RoleBM> roleBMS = new HashSet<>(roles.size());
        for (String role : roles){
            RoleBM roleBM = new RoleBM(role.equals(RoleTypeBM.ROLE_USER.name())? RoleTypeBM.ROLE_USER : RoleTypeBM.ROLE_ADMIN);
            roleBMS.add(roleBM);
        }

        return roleBMS;
    }
}
