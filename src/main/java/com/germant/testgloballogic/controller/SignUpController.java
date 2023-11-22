package com.germant.testgloballogic.controller;

import com.germant.testgloballogic.exeptions.RegisterException;
import com.germant.testgloballogic.mapper.RegisterMapper;
import com.germant.testgloballogic.mapper.UserMapper;
import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.request.UserRequest;
import com.germant.testgloballogic.model.response.RegisterResponse;
import com.germant.testgloballogic.security.jwt.JWTGenerator;
import com.germant.testgloballogic.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private UserServiceImpl userService;
    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;

    @Autowired
    public SignUpController(UserServiceImpl userService, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRequest userRequest) {
        RegisterResponse registerResponse = null;
        if (!userService.existByEmail(userRequest.getEmail())) {
            userService.save(UserMapper.mapper(userRequest));
            registerResponse = RegisterMapper.mapper(userService.findUserByEmail(userRequest.getEmail()));

            UserEntity user = userService.findUserByEmail(userRequest.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            userRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            registerResponse.setToken(jwtGenerator.generateToken(authentication));
        } else {
             throw new RegisterException("Error to register");
        }
        return ResponseEntity.ok().body(registerResponse);
    }
}
