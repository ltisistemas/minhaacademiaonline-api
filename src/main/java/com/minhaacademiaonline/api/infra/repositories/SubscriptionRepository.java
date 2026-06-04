package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.Subscription;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    Subscription findSubscriptionById(UUID id);

    Subscription findSubscriptionByTenant(Tenant tenant);

    Optional<Subscription> findFirstByTenantIdAndStatusOrderByCreatedAtDesc(
            UUID tenantId,
            Subscription.SubscriptionStatus status
    );
}
