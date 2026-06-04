package com.minhaacademiaonline.api.domain.dtos;

import java.util.UUID;

public record AuthRegisterResponseDto(
        UUID sub,
        String userName,
        String userEmail,
        String access_token,
        UUID tenantId,
        String TradeName,
        String subdomain,
        UUID subscriptionId
) {}
