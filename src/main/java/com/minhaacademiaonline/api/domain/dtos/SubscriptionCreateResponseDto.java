package com.minhaacademiaonline.api.domain.dtos;

import com.minhaacademiaonline.api.domain.entities.Subscription;
import com.minhaacademiaonline.api.domain.entities.Tenant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionCreateResponseDto(
        UUID id,
        Tenant tenant,
        BigDecimal amount,
        LocalDate nextDueDate,
        Subscription.SubscriptionStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
