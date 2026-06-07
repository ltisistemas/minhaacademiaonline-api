package com.minhaacademiaonline.api.application.exceptions;

public class BadCredentialsException extends UserException{
    public BadCredentialsException(String message) {
        super(message);
    }
}
