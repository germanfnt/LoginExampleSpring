package com.germant.testgloballogic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.request.LoginRequest;
import com.germant.testgloballogic.model.response.LoginResponse;
import com.germant.testgloballogic.security.jwt.JWTGenerator;
import com.germant.testgloballogic.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LogInControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTGenerator jwtGenerator;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private LogInController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPass("password");

        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", "password");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setEmail("test@example.com");
        expectedResponse.setToken("fakeToken");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userService.findUserByEmail("test@example.com")).thenReturn(userEntity);
        when(jwtGenerator.generateToken(authentication)).thenReturn("fakeToken");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/login")
                .content(asJsonString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.token").value("fakeToken"))
                .andReturn();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}