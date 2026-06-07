package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.interfaces.*;
import com.minhaacademiaonline.api.domain.dtos.*;
import com.minhaacademiaonline.api.domain.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final TenantRegistrationOrchestrator _orchestrator;
    private final ISubscriptionService _subscriptionService;
    private final ITokenService _tokenService;
    private final AuthAuthenticator _authAuthenticator;
    private final AuthMapper _authMapper;

    @Override
    @Transactional
    public AuthSignUpResponseDto signUp(AuthSignUpRequestDto req) {
        AuthResultSignUp authResult = _orchestrator.register(req);

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
                authResult.user().getUsername()
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
