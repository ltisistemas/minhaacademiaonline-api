package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.interfaces.ISubscriptionService;
import com.minhaacademiaonline.api.domain.dtos.SubscriptionCreateRequestDto;
import com.minhaacademiaonline.api.domain.entities.Subscription;
import com.minhaacademiaonline.api.infra.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService  {
    private final SubscriptionRepository _repository;

    @Override
    public Subscription create(SubscriptionCreateRequestDto req) {
        Subscription sub = new Subscription();
        sub.setTenant(req.tenant());
        sub.setAmount(req.amount());
        sub.setNextDueDate(req.nextDueDate());
        sub.setStatus(req.status());

        return _repository.save(sub);
//        Subscription subscription = _repository.save(sub);
//        return new SubscriptionCreateResponseDto(
//                subscription.getId(),
//                subscription.getTenant(),
//                subscription.getAmount(),
//                subscription.getNextDueDate(),
//                subscription.getStatus(),
//                subscription.getCreatedAt(),
//                subscription.getUpdatedAt()
//        );
    }

    @Override
    public Optional<Subscription> findFirstByTenantIdAndStatusOrderByCreatedAtDesc(UUID tenantId,
                                                                                   Subscription.SubscriptionStatus status) {
        return _repository.findFirstByTenantIdAndStatusOrderByCreatedAtDesc(
                tenantId,
                Subscription.SubscriptionStatus.PENDING
        );

    }
}
