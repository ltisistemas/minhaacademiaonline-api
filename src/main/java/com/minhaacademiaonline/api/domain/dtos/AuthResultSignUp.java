package com.minhaacademiaonline.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minhaacademiaonline.api.domain.entities.Subscription;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;
import jakarta.annotation.Nullable;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthResultSignUp(Tenant tenant, User user, SubscriptionDto subscription) {
}
