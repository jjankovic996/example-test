package org.example.clean.architecture.repository.impl_repository;

import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.repository.jpa_repository.RoleRepositoryJPA;
import org.example.clean.architecture.repository.jpa_repository.UserRepositoryJPA;
import org.example.clean.architecture.repository.mapper.UserMapper;
import org.example.clean.architecture.repository.model.User;
import org.example.clean.architecture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJPA userRepositoryJPA;
    private final RoleRepositoryJPA roleRepositoryJPA;
    private final UserMapper userMapper;

    @Autowired
    public  UserRepositoryImpl(UserRepositoryJPA userRepositoryJPA,
                               RoleRepositoryJPA roleRepositoryJPA,
                               UserMapper userMapper){
        this.userRepositoryJPA =userRepositoryJPA;
        this.roleRepositoryJPA = roleRepositoryJPA;
        this.userMapper = userMapper;
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
        User user = userMapper.toUser(userBM);

        user.getRoles().forEach(role -> {
            roleRepositoryJPA.save(role);

        });
        user = userRepositoryJPA.save(user);

        return userMapper.toUserBM(user);
    }
}
