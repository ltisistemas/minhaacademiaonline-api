package com.minhaacademiaonline.api.adapters.in.web.exceptions;

public class StudentNotFoundException extends StudentException {
    public StudentNotFoundException(String message, RuntimeException e) {
        super(message, e);
    }
}
