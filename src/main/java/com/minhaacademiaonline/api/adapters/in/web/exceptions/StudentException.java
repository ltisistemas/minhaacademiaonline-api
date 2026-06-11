package com.minhaacademiaonline.api.adapters.in.web.exceptions;

public class StudentException extends RuntimeException {
    public StudentException(String message, RuntimeException e) {
        super(message, e);
    }
}
