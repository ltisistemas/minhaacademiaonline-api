package com.minhaacademiaonline.api.domain.dtos;

import com.minhaacademiaonline.api.domain.entities.Plan;
import com.minhaacademiaonline.api.domain.entities.Tenant;

import java.math.BigDecimal;
import java.util.UUID;

public record TenantCreateDto(
        Plan plan,
        String legalName,
        String tradeName,
        String nif,
        Tenant.TenantPaidStatus paidStatus
) {
}
