package org.example.clean.architecture;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserBM {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<RoleBM> roles;

    public UserBM(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }
}