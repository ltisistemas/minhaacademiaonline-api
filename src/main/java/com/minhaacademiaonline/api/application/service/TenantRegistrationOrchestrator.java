package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.Exceptions.PlanNotFoundException;
import com.minhaacademiaonline.api.application.interfaces.ISubscriptionService;
import com.minhaacademiaonline.api.application.interfaces.ITenantService;
import com.minhaacademiaonline.api.application.utils.DateUtils;
import com.minhaacademiaonline.api.domain.dtos.*;
import com.minhaacademiaonline.api.domain.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenantRegistrationOrchestrator {
    private final ITenantService _service;
    private final PlanService _planServicce;
    private final UserService _userService;
    private final ISubscriptionService _subscriptionService;
    private final PasswordEncoder _passwordEncoder;

    AuthResultSignUp register(AuthSignUpRequestDto req) {
        Plan plan = Optional
                .ofNullable(_planServicce.findById(req.selectedPlan()))
                .orElseThrow(() -> new PlanNotFoundException("Plan not found"));

        var paidStatus = plan.getPaidStatus();
        Tenant tenant = _service.create(
                new TenantCreateDto(
                        plan,
                        req.legalName(),
                        req.tradeName(),
                        req.nif(),
                        paidStatus
                )
        );

        User user = _userService.create(
                new UserCreateRequestDto(
                        req.userEmail(),
                        req.userName(),
                        req.userPassword()
                )
        );

        // Associaar Usuario ao Tenant
        _service.userAssign(
                new UserTenantAssignCreateDto(
                        user,
                        tenant,
                        UserTenant.UserRole.OWNER
                )
        );

        Subscription subscription = null;
        if (paidStatus == Tenant.TenantPaidStatus.PENDING) {
            subscription = Optional.ofNullable(_subscriptionService.create(new SubscriptionCreateRequestDto(
                    tenant,
                    plan.getFee(),
                    DateUtils.getNextBusinessDay(),
                    Subscription.SubscriptionStatus.PENDING
            ))).orElse(null);
        }

        return new AuthResultSignUp(tenant, user, subscription);
    }
}
