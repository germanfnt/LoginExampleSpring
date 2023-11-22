package com.germant.testgloballogic.controller;

import com.germant.testgloballogic.mapper.LoginMapper;
import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.request.LoginRequest;
import com.germant.testgloballogic.model.response.LoginResponse;
import com.germant.testgloballogic.security.jwt.JWTGenerator;
import com.germant.testgloballogic.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogInController {

    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;

    private UserServiceImpl userService;

    @Autowired
    public LogInController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPass()));

        UserEntity entity = userService.findUserByEmail(loginRequest.getEmail());
        loginResponse = LoginMapper.mapper(entity);
        loginResponse.setToken(jwtGenerator.generateToken(authentication));
        userService.updateLastLogin(loginRequest.getEmail());

        return ResponseEntity.ok(loginResponse);
    }

}