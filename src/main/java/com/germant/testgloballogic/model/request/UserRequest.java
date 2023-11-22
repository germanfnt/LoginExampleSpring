package com.germant.testgloballogic.model.request;

import com.germant.testgloballogic.util.validator.PassValidator;
import com.germant.testgloballogic.util.validator.ValidEmail;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String id;

    @NotNull
    private String name;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    @PassValidator
    private String password;

    private PhoneRequest phone;

    private String token;

    private LocalDateTime register;

    private LocalDateTime lastAccess;

}
