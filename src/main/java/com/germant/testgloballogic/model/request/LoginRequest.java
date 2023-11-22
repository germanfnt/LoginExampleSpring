package com.germant.testgloballogic.model.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String email;
    private String pass;
}
