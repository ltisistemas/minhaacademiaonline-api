package com.minhaacademiaonline.api.domain.dtos;

import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;

public record AuthResult(Tenant tenant, User user) {
}
