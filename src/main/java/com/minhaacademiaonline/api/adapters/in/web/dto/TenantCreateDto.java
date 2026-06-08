package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.Plan;
import com.minhaacademiaonline.api.domain.entities.Tenant;

public record TenantCreateDto(
        Plan plan,
        String legalName,
        String tradeName,
        String nif,
        Tenant.TenantPaidStatus paidStatus
) {
}
