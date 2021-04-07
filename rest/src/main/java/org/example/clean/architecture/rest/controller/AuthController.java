package org.example.clean.architecture.rest.controller;

import org.example.clean.architecture.JwtToken;
import org.example.clean.architecture.rest.mapper.LoginMapper;
import org.example.clean.architecture.rest.mapper.SignupMapper;
import org.example.clean.architecture.rest.request.LoginRequest;
import org.example.clean.architecture.rest.request.SignupRequest;
import org.example.clean.architecture.rest.security.services.RedisService;
import org.example.clean.architecture.usecase.AuthenticateUseCase;
import org.example.clean.architecture.usecase.RegisterUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    private final RedisService redisService;

    @Autowired
    public AuthController(RegisterUserCase registerUserCase,
                          AuthenticateUseCase authenticateUseCase,
                          SignupMapper signupMapper,
                          LoginMapper loginMapper,
                          RedisService redisService) {
        this.registerUserCase = registerUserCase;
        this.authenticateUseCase = authenticateUseCase;
        this.signupMapper = signupMapper;
        this.loginMapper = loginMapper;
        this.redisService = redisService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) throws Exception{
        return ResponseEntity.ok(registerUserCase.register(signupMapper.toUser(signUpRequest)));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        JwtToken jwtToken = authenticateUseCase.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(loginMapper.toLoginResponse(jwtToken));
    }

    @GetMapping
    public ResponseEntity<?> logout(@RequestHeader  HttpHeaders headers){
        redisService.blacklistToken(extractToken(headers));
        return ResponseEntity.ok().build();
    }

    private String extractToken(HttpHeaders headers){
        String bearerToken = headers.get("authorization").get(0);
        return bearerToken.substring(7);
    }

}
