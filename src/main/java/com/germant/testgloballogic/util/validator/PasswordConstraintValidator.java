package com.germant.testgloballogic.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordConstraintValidator implements ConstraintValidator<PassValidator, String> {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=(.*\\d){2,})(?=.*[a-z]).{8,12}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public void initialize(final PassValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, final ConstraintValidatorContext context) {

        Matcher matcher = pattern.matcher(value);
        if (matcher.matches() == true) {
            return true;
        } else {
            return false;
        }

    }
}
