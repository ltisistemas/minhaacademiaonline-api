package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.adapters.in.web.dto.TenantCreateDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.UserTenantAssignCreateDto;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.UserTenant;

import java.util.List;
import java.util.UUID;

public interface ITenantService {
    Tenant create(TenantCreateDto req);
    UserTenant userAssign(UserTenantAssignCreateDto req);
    List<Tenant> findAll();
    Tenant findById(UUID id);
    Tenant findTenantBySubdomain(String subdomain);
    Tenant findTenantWithSubscription(UUID id);
    Tenant findTenantWithSubscription(String subdomain);
}
