package com.minhaacademiaonline.api.domain.dtos;

import com.minhaacademiaonline.api.domain.entities.Subscription;

import java.util.UUID;

public record AuthSignInResponseDto(
        UUID sub,
        String userName,
        String userEmail,
        String access_token,
        UUID tenantId,
        String tradeName,
        String subdomain,
        Subscription subscription
) {

}
