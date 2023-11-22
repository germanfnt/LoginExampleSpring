package com.germant.testgloballogic.model.response;


import com.germant.testgloballogic.model.request.PhoneRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String id;
    private LocalDateTime created;
    private LocalDateTime lastAcces;
    private String token;
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private PhoneRequest phone;
}
