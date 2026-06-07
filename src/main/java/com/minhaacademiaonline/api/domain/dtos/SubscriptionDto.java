package com.minhaacademiaonline.api.domain.dtos;

import com.minhaacademiaonline.api.domain.entities.Subscription;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SubscriptionDto(
        UUID id,
        BigDecimal amount,
        LocalDate nextDueDate,
        Subscription.SubscriptionStatus status) {
}
