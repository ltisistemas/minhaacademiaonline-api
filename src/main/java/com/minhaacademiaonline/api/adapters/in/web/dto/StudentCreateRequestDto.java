package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.Belt;

import java.util.UUID;

public record StudentCreateRequestDto(
        UUID beltId,
        String subdomain,
        String name,
        String nickname,
        String username,
        String password,
        String phoneNumber
) {
}
