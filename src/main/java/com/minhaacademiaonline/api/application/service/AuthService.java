package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.interfaces.IAuthService;
import com.minhaacademiaonline.api.application.interfaces.ISubscriptionService;
import com.minhaacademiaonline.api.application.interfaces.ITenantService;
import com.minhaacademiaonline.api.application.interfaces.ITokenService;
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

    @Override
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
        Tenant tenant = _tenantService.findTenantBySubdomain(req.subdomain());
        if (tenant == null) {
            throw new RuntimeException("Tenant not found");
        }

        User user = _userService.loadUserByUsername(req.username());
        if (user != null) {
            if (_passwordEncoder.matches(req.password(), user.getPassword())) {
                String access_token = _tokenService.generateToken(tenant.getId(), user.getId(), tenant.getSubdomain(), user.getUsername());

                Tenant.TenantPaidStatus paidStatus = tenant.getPlan().getFee().equals(BigDecimal.ZERO)
                        ? Tenant.TenantPaidStatus.PAID
                        : Tenant.TenantPaidStatus.PENDING;

                    UUID subscriptionId = _subscriptionService
                        .findFirstByTenantIdAndStatusOrderByCreatedAtDesc(tenant.getId(), Subscription.SubscriptionStatus.PENDING)
                        .map(Subscription::getId)
                        .orElse(null);

                return new AuthSignInResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        access_token,
                        tenant.getId(),
                        tenant.getTradeName(),
                        tenant.getSubdomain(),
                        subscriptionId
                );
            }

            throw new RuntimeException("[002] Username or Passowrd is invalid");
        }

        throw new RuntimeException("[001] Username or Passowrd is invalid");
    }
}
