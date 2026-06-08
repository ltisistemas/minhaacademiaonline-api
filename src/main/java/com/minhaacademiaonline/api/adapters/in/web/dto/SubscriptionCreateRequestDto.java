package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.Subscription;
import com.minhaacademiaonline.api.domain.entities.Tenant;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionCreateRequestDto(
        Tenant tenant,
        BigDecimal amount,
        LocalDate nextDueDate,
        Subscription.SubscriptionStatus status
) {
}

