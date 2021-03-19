package org.example.clean.architecture.usecase;

import lombok.extern.slf4j.Slf4j;
import org.example.clean.architecture.Role;
import org.example.clean.architecture.RoleType;
import org.example.clean.architecture.User;
import org.example.clean.architecture.exception.RegisterException;
import org.example.clean.architecture.repository.RoleRepository;
import org.example.clean.architecture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RegisterService implements RegisterUserCase{

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

        User newUser = new User(user.getUsername(), user.getEmail(), passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = setRolesForUser(newUser, user.getRoles());

        newUser.setRoles(roles);
        userRepository.save(newUser);

        return newUser;
    }

    private Set<Role> setRolesForUser(User newUser, Set<Role> requestRoles){
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

        return roles;
    }
}
