package com.minhaacademiaonline.api.adapters.in.web.exceptions;

public class GatewayTimeoutException extends RuntimeException {
    public GatewayTimeoutException(String message) {
        super(message);
    }
}
