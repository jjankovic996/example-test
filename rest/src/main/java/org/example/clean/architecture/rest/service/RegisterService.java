package org.example.clean.architecture.rest.service;

import lombok.extern.slf4j.Slf4j;
import org.example.clean.architecture.Role;
import org.example.clean.architecture.RoleType;
import org.example.clean.architecture.User;
import org.example.clean.architecture.exception.RegisterException;
import org.example.clean.architecture.repository.RoleRepository;
import org.example.clean.architecture.repository.UserRepository;
import org.example.clean.architecture.usecase.RegisterUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RegisterService implements RegisterUserCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public RegisterService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User register(User user) throws RegisterException {

        if (userRepository.countByUsername(user.getUsername()) != 0) {
            throw new RegisterException("Username already exists");
        }

        if (userRepository.countByEmail(user.getEmail()) != 0) {
            throw new RegisterException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRolesForUser(user, user.getRoles());

        user = userRepository.save(user);

        return user;
    }

    private void setRolesForUser(User user, Set<Role> requestRoles){
        Set<Role> roles = new HashSet<>();

        if (requestRoles == null || requestRoles.size() == 0) {
            Role role = roleRepository.findById(2).get();
            roles.add(role);
        } else {
            requestRoles.forEach(roleRequest -> {
                String roleType = roleRequest.getName().name();

                Role role = roleType.equals(RoleType.ROLE_ADMIN.name()) ? roleRepository.findById(1).get(): roleRepository.findById(2).get();
                roles.add(role);
            });
        }

        user.setRoles(roles);
    }
}
