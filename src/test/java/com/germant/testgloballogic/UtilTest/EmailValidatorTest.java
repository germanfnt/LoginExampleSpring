package com.germant.testgloballogic.UtilTest;

import com.germant.testgloballogic.util.validator.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    @Test
    public void testValidEmail() {
        EmailValidator validator = new EmailValidator();
        assertTrue(validator.isValid("german@test.com.ar", null));
    }

    @Test
    public void testInvalidEmail() {
        EmailValidator validator = new EmailValidator();
        assertFalse(validator.isValid("invalid.email", null));
    }

}
