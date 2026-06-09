package com.minhaacademiaonline.api.application.mappers;

import com.minhaacademiaonline.api.adapters.in.web.dto.*;
import com.minhaacademiaonline.api.domain.entities.Subscription;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    // Mapeamento para o Login
    @Mapping(target = "sub", source = "result.user.id")
    @Mapping(target = "userName", source = "result.user.name")
    @Mapping(target = "userEmail", source = "result.user.username")
    @Mapping(target = "tenantId", source = "result.tenant.id")
    @Mapping(target = "tradeName", source = "result.tenant.tradeName")
    @Mapping(target = "subdomain", source = "result.tenant.subdomain")
    @Mapping(target = "access_token", source = "accessToken")
    @Mapping(target = "subscription", source = "subscription")
    AuthSignInResponseDto toSignInResponse(AuthResult result, String accessToken, Subscription subscription);

    // Mapeamento para o Register
    @Mapping(target = "sub", source = "result.user.id")
    @Mapping(target = "userName", source = "result.user.name")
    @Mapping(target = "userEmail", source = "result.user.username")
    @Mapping(target = "tenantId", source = "result.tenant.id")
    @Mapping(target = "tradeName", source = "result.tenant.tradeName")
    @Mapping(target = "subdomain", source = "result.tenant.subdomain")
    @Mapping(target = "access_token", source = "accessToken")
    @Mapping(target = "subscription", source = "subscription")
    AuthSignUpResponseDto toSignUpResponse(AuthResultSignUp result, String accessToken, SubscriptionDto subscription);

    // Mapeamento para o Subscription
    @Mapping(target = "id", source = "subscription.id")
    @Mapping(target = "amount", source = "subscription.amount")
    @Mapping(target = "nextDueDate", source = "subscription.nextDueDate")
    @Mapping(target = "status", source = "subscription.status")
    SubscriptionDto toSubscription(Subscription subscription);
}
