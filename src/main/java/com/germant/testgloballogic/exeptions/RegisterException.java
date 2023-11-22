package com.germant.testgloballogic.exeptions;

public class RegisterException extends RuntimeException{
    public RegisterException(String message) {
        super(message);
    }
}
