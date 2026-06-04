package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.Exceptions.*;
import com.minhaacademiaonline.api.application.interfaces.*;
import com.minhaacademiaonline.api.application.utils.DateUtils;
import com.minhaacademiaonline.api.domain.dtos.*;
import com.minhaacademiaonline.api.domain.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final ITenantService _tenantService;
    private final PlanService _planServicce;
    private final UserService _userService;
    private final ISubscriptionService _subscriptionService;
    private final ITokenService _tokenService;
    private final PasswordEncoder _passwordEncoder;
    private final AuthAuthenticator _authAuthenticator;
    private final AuthMapper _authMapper;

    @Override
    @Transactional
    public AuthRegisterResponseDto signUp(AuthRegisterRequestDto req) {
        Plan plan = _planServicce.findById(req.selectedPlan());

        Tenant.TenantPaidStatus paidStatus = plan.getFee().equals(BigDecimal.ZERO)
                ? Tenant.TenantPaidStatus.PAID
                : Tenant.TenantPaidStatus.PENDING;

        // Salvar o Tenant
        Tenant tenant = _tenantService.create(
                new TenantCreateDto(
                        plan,
                        req.legalName(),
                        req.tradeName(),
                        req.nif(),
                        paidStatus
                )
        );

        // Salvar o Usuário
        User user = _userService.create(
                new UserCreateRequestDto(
                        req.userEmail(),
                        req.userName(),
                        req.userPassword()
                )
        );

        // Associaar Usuario ao Tenant
        _tenantService.userAssign(
                new UserTenantAssignCreateDto(
                        user,
                        tenant,
                        UserTenant.UserRole.OWNER
                )
        );

        // Verifica se é um plano pago
        SubscriptionCreateResponseDto subscription = null;
        if (paidStatus == Tenant.TenantPaidStatus.PENDING) {
            subscription = _subscriptionService.create(new SubscriptionCreateRequestDto(
                    tenant,
                    plan.getFee(),
                    DateUtils.getNextBusinessDay(),
                    Subscription.SubscriptionStatus.PENDING
            ));
        }

        // Devolve o usuário Logado
        String access_token = _tokenService.generateToken(tenant.getId(), user.getId(), tenant.getSubdomain(), user.getUsername());
        return new AuthRegisterResponseDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                access_token,
                tenant.getId(),
                tenant.getTradeName(),
                tenant.getSubdomain(),
                subscription != null ? subscription.id() : null
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AuthSignInResponseDto signIn(AuthSignInRequestDto req) {
        AuthResult authResult = _authAuthenticator.authenticate(req);

        String token = _tokenService.generateToken(
                authResult.tenant().getId(),
                authResult.user().getId(),
                req.subdomain(),
                authResult.user().getName()
        );

        Subscription subscription = getSubscription(authResult.tenant());

        return _authMapper.toSignInResponse(authResult,token,subscription);
    }

    private Subscription getSubscription(Tenant tenant) {
        return tenant.getPaidStatus() == Tenant.TenantPaidStatus.PENDING
                ? _subscriptionService
                    .findFirstByTenantIdAndStatusOrderByCreatedAtDesc(tenant.getId(), Subscription.SubscriptionStatus.PENDING)
                    .orElse(null)
                : null;
    }
}
