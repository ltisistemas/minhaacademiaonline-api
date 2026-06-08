package com.minhaacademiaonline.api.domain.entities;

import java.util.UUID;

public record TenantCreatedEvent(
        UUID tenantId,
        UUID planId
) {
}
