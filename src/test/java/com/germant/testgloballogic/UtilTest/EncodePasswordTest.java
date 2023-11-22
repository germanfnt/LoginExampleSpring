package com.germant.testgloballogic.UtilTest;

import com.germant.testgloballogic.util.EncodePassword;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class EncodePasswordTest {
    @Test
    void testEncode() {
        String password = "mamama";
        String encodedPassword = EncodePassword.encode(password);

        assertNotNull(encodedPassword);
        assertNotEquals(password, encodedPassword);

        assertTrue(new BCryptPasswordEncoder().matches(password, encodedPassword));
    }
}