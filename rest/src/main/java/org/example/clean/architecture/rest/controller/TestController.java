package org.example.clean.architecture.rest.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testUser(){
        return "User can only see this.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdmin(){
        return "Admin can only see this.";
    }


    @GetMapping("/both")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public String testBoth(){
        return "User and admin can see this.";
    }

    @GetMapping("/all")
    public String testAll(){
        return "Anyone can see this.";
    }

}
