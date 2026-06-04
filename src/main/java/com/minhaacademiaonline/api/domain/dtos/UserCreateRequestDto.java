package com.minhaacademiaonline.api.domain.dtos;

public record UserCreateRequestDto(String userName,
                                   String userEmail,
                                   String userPassword) {
}
