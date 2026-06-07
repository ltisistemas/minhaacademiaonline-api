package com.minhaacademiaonline.api.application.dtos;

import java.util.UUID;

public record AuthSignInResponseDto(
        UUID sub,
        String userName,
        String userEmail,
        String access_token,
        UUID tenantId,
        String tradeName,
        String subdomain,
        SubscriptionDto subscription
) {

}
