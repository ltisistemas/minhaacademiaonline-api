package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.interfaces.*;
import com.minhaacademiaonline.api.application.utils.DateUtils;
import com.minhaacademiaonline.api.domain.dtos.*;
import com.minhaacademiaonline.api.domain.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private TenantRegistrationOrchestrator orchestrator;
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
    public AuthSignUpResponseDto signUp(AuthSignUpRequestDto req) {
        AuthResultSignUp authResult = orchestrator.register(req);

        String token = _tokenService.generateToken(
                authResult.tenant().getId(),
                authResult.user().getId(),
                authResult.tenant().getSubdomain(),
                authResult.user().getName()
        );

        return _authMapper.toSignUpResponse(authResult, token, authResult.subscription());
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
