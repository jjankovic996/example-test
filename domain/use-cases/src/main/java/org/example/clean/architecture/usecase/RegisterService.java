package org.example.clean.architecture.usecase;

import org.example.clean.architecture.RoleBM;
import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.exception.RegisterException;
import org.example.clean.architecture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import  static org.example.clean.architecture.RoleTypeBM.ROLE_USER;
import  static org.example.clean.architecture.RoleTypeBM.ROLE_ADMIN;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService implements RegisterUserCase{

    private static final RoleBM ADMIN = new RoleBM(ROLE_ADMIN);
    private static final RoleBM USER = new RoleBM(ROLE_USER);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserBM register(UserBM userBM) throws RegisterException {
        if (userRepository.doesUserExistByUsername(userBM.getUsername())) {
            throw new RegisterException("Username already exists");
        }

        if (userRepository.doesUserExistByEmail(userBM.getEmail())) {
            throw new RegisterException("Username already exists");
        }


        UserBM user = new UserBM(userBM.getUsername(), userBM.getEmail(),
                passwordEncoder.encode(userBM.getPassword()));

        Set<RoleBM> roles = new HashSet<>();

        if (userBM.getRoles() == null || userBM.getRoles().size() == 0) {
            roles.add(USER);
        } else {
            userBM.getRoles().forEach(role -> {

                if (role.equals(ROLE_ADMIN.name())) {
                    roles.add(ADMIN);
                }else{
                    roles.add(USER);
                }

            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}
