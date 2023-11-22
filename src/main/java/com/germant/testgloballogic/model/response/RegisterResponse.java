package com.germant.testgloballogic.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private String id;

    private LocalDateTime created;

    private LocalDateTime lastLogin;

    private String token;

    private boolean isActive;

}
