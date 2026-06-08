package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthResultSignUp(Tenant tenant, User user, SubscriptionDto subscription) {
}
