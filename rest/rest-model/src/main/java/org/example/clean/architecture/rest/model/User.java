package org.example.clean.architecture.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
}