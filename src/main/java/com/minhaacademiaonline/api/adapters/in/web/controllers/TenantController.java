package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.application.service.TenantService;
import com.minhaacademiaonline.api.adapters.in.web.dto.TenantCreateDto;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService _service;

    @PostMapping
    public Tenant create(@RequestBody TenantCreateDto req) {
        return _service.create(req);
    }
}
