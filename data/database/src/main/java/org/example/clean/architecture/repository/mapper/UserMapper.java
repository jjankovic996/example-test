package org.example.clean.architecture.repository.mapper;

import org.example.clean.architecture.UserBM;
import org.example.clean.architecture.repository.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserBM  toUserBM(User user){
        UserBM  userBM = new UserBM(user.getEmail(), user.getUsername(), user.getPassword());
        return userBM;
    }

    public User toUser(UserBM userBM){
        User user = new User(userBM.getEmail(), userBM.getUsername(), userBM.getPassword());
        return user;
    }
}
