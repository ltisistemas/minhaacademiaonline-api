package com.minhaacademiaonline.api.application.exceptions;

public class UserEmailExistsException extends UserException {
    public UserEmailExistsException(String message) {
        super(message);
    }
}
