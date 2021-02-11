package org.example.clean.architecture.usecase;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.RoleTypeBM;
import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.exception.RegisterException;
import org.example.clean.architecture.repository.RoleRepository;
import org.example.clean.architecture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import  static org.example.clean.architecture.RoleTypeBM.ROLE_USER;
import  static org.example.clean.architecture.RoleTypeBM.ROLE_ADMIN;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService implements RegisterUserCase{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserBM register(String username, String password, Set<RoleBM> roles, String email) throws RegisterException {
        if (userRepository.doesUserExistByUsername(username)) {
            throw new RegisterException("Username already exists");
        }

        if (userRepository.doesUserExistByEmail(email)) {
            throw new RegisterException("Username already exists");
        }


        UserBM user = new UserBM(username, email, passwordEncoder.encode(password));


        if (roles == null) {
            RoleBM userRole = roleRepository.findByName(ROLE_USER.name());
            roles.add(userRole);
        } else {
            roles.forEach(role -> {

                if (role.equals(ROLE_ADMIN.name())) {
                    RoleBM adminRole = roleRepository.findByName(ROLE_ADMIN.name());
                    roles.add(adminRole);
                }else{
                    RoleBM userRole = roleRepository.findByName(ROLE_USER.name());
                    roles.add(userRole);
                }

            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}
