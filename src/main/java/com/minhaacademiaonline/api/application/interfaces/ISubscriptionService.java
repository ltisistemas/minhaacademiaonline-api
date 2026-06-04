package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.domain.dtos.SubscriptionCreateRequestDto;
import com.minhaacademiaonline.api.domain.dtos.SubscriptionCreateResponseDto;
import com.minhaacademiaonline.api.domain.entities.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface ISubscriptionService {
    Subscription create(SubscriptionCreateRequestDto req);

    Optional<Subscription> findFirstByTenantIdAndStatusOrderByCreatedAtDesc(UUID tenantId,
                                                                            Subscription.SubscriptionStatus status);
}
