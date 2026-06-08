package com.minhaacademiaonline.api.adapters.in.web.dto;

public record UserCreateRequestDto(String userName,
                                   String userEmail,
                                   String userPassword) {
}
