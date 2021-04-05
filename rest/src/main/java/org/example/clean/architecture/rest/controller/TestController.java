package org.example.clean.architecture.rest.controller;


import org.example.clean.architecture.rest.security.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private RedisService service;

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
    @GetMapping("/redis/{key}/{value}")
    public ResponseEntity<?> testRedisPostUser(@PathVariable String key, @PathVariable String value){
        service.setValue(key,value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/redis/{key}")
    public ResponseEntity<?> testRedisGetUser(@PathVariable String key){
        Object o = service.getValue(key);
        return ResponseEntity.ok(o);
    }



}
