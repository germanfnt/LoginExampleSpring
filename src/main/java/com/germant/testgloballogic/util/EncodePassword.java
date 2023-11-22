package com.germant.testgloballogic.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePassword {

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
