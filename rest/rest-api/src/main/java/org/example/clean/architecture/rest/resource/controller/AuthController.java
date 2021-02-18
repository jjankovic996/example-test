package org.example.clean.architecture.rest.resource.controller;

import org.example.clean.architecture.rest.model.request.SignupRequest;
import org.example.clean.architecture.rest.resource.mapper.SignupMapper;
import org.example.clean.architecture.usecase.RegisterUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserCase registerUserCase;
    private final SignupMapper signupMapper;

    @Autowired
    public AuthController(RegisterUserCase registerUserCase, SignupMapper signupMapper) {
        this.registerUserCase = registerUserCase;
        this.signupMapper = signupMapper;
    }

    @GetMapping
    public String test(){
        return "jelena";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception{
        return ResponseEntity.ok(registerUserCase.register(signupMapper.toUserBM(signUpRequest)));
    }
}
