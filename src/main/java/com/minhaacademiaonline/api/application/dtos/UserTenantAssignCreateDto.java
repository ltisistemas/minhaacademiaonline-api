package com.minhaacademiaonline.api.application.dtos;

import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;
import com.minhaacademiaonline.api.domain.entities.UserTenant;

public record UserTenantAssignCreateDto(
        User user,
        Tenant tenant,
        UserTenant.UserRole role
) {
}
