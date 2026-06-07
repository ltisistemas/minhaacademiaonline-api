package com.minhaacademiaonline.api.application.dtos;

public record UserCreateRequestDto(String userName,
                                   String userEmail,
                                   String userPassword) {
}
