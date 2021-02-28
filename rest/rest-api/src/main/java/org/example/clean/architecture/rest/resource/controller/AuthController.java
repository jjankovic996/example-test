package org.example.clean.architecture.rest.resource.controller;

import org.example.clean.architecture.JwtToken;
import org.example.clean.architecture.rest.model.request.LoginRequest;
import org.example.clean.architecture.rest.model.request.SignupRequest;
import org.example.clean.architecture.rest.resource.mapper.LoginMapper;
import org.example.clean.architecture.rest.resource.mapper.SignupMapper;
import org.example.clean.architecture.usecase.AuthenticateUseCase;
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
    private final AuthenticateUseCase authenticateUseCase;
    private final SignupMapper signupMapper;
    private final LoginMapper loginMapper;

    @Autowired
    public AuthController(RegisterUserCase registerUserCase,
                          AuthenticateUseCase authenticateUseCase,
                          SignupMapper signupMapper,
                          LoginMapper loginMapper) {
        this.registerUserCase = registerUserCase;
        this.authenticateUseCase = authenticateUseCase;
        this.signupMapper = signupMapper;
        this.loginMapper = loginMapper;
    }

    @GetMapping
    public String test(){
        return "jelena";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) throws Exception{
        return ResponseEntity.ok(registerUserCase.register(signupMapper.toUserBM(signUpRequest)));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
        JwtToken jwtToken = authenticateUseCase.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(loginMapper.toLoginResponse(jwtToken));
    }
}