package com.minhaacademiaonline.api.application.dtos;

import com.minhaacademiaonline.api.domain.entities.Subscription;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;

public record AuthResult(Tenant tenant, User user) {
}

