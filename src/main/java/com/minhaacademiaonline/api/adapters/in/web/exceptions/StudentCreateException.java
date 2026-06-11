package com.minhaacademiaonline.api.adapters.in.web.exceptions;

public class StudentCreateException extends StudentException{
    public StudentCreateException(String message, RuntimeException e) {
        super(message, e);
    }
}
