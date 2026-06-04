package com.minhaacademiaonline.api.domain.dtos;

public record AuthSignInRequestDto(String subdomain, String username, String password) {
}
