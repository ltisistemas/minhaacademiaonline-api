package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.interfaces.ITenantService;
import com.minhaacademiaonline.api.application.dtos.TenantCreateDto;
import com.minhaacademiaonline.api.application.dtos.UserTenantAssignCreateDto;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.UserTenant;
import com.minhaacademiaonline.api.infra.repositories.TenantRepository;
import com.minhaacademiaonline.api.infra.repositories.UserTenantRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService implements ITenantService {
    private final TenantRepository _repository;
    private final UserTenantRepository _userTenantRepository;

    @Override
    public Tenant create(TenantCreateDto req) {
        Tenant ten = new Tenant();
        ten.setLegalName(req.legalName());
        ten.setTradeName(req.tradeName());
        ten.setNif(req.nif());
        ten.setSubdomain(ten.generteSubdomain());
        ten.setPlan(req.plan());
        ten.setMonthlyFee(req.plan().getFee());
        ten.setPaidStatus(req.paidStatus());

        return _repository.save(ten);
    }

    public UserTenant userAssign(UserTenantAssignCreateDto req) {
        UserTenant ut = new UserTenant();
        ut.setUser(req.user());
        ut.setTenant(req.tenant());
        ut.setRole(req.role());

        return _userTenantRepository.save(ut);
    }

    @Override
    public List<Tenant> findAll() {
        return List.of();
    }

    @Override
    public Tenant findById(UUID id) {
        return null;
    }

    @Override
    public @Nullable Tenant findTenantBySubdomain(String subdomain) {
        return _repository.findTenantBySubdomain(subdomain).orElse(null);
    }

    @Override
    public @Nullable Tenant findTenantWithSubscription(UUID id) {
        return (Tenant) _repository.findTenantById(id).orElse(null);
    }

    @Override
    public @Nullable Tenant findTenantWithSubscription(String subdomain) {
        return _repository.findTenantBySubdomain(subdomain).orElse(null);
    }
}
