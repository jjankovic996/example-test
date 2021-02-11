package org.example.clean.architecture.persistence.impl_repository;

import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.persistence.jpa_repository.UserRepositoryJPA;
import org.example.clean.architecture.persistence.mapper.RoleMapper;
import org.example.clean.architecture.persistence.mapper.UserMapper;
import org.example.clean.architecture.persistence.model.Role;
import org.example.clean.architecture.persistence.model.User;
import org.example.clean.architecture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJPA userRepositoryJPA;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public  UserRepositoryImpl(UserRepositoryJPA userRepositoryJPA,
                               UserMapper userMapper,
                               RoleMapper roleMapper){
        this.userRepositoryJPA =userRepositoryJPA;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserBM findUserByUsername(String username) {
        User user = userRepositoryJPA.findByUsername(username).get();
        return userMapper.toUserBM(user);
    }

    @Override
    public Boolean doesUserExistByUsername(String username) {
        return userRepositoryJPA.existsByUsername(username);
    }

    @Override
    public Boolean doesUserExistByEmail(String email) {
        return userRepositoryJPA.existsByEmail(email);
    }

    @Override
    public UserBM save(UserBM userBM) {
        User user = userRepositoryJPA.save(userMapper.toUser(userBM));
        Set<Role> roles = new HashSet<>();

        userBM.getRoles().forEach(roleBM -> {
            Role role = roleMapper.toRole(roleBM);
            roles.add(role);
        });

        user.setRoles(roles);

        user = userRepositoryJPA.save(user);

        return userMapper.toUserBM(user);
    }
}
