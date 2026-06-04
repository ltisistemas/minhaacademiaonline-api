package com.minhaacademiaonline.api.application.controllers;

import com.minhaacademiaonline.api.application.service.PlanService;
import com.minhaacademiaonline.api.application.service.TenantService;
import com.minhaacademiaonline.api.domain.dtos.PlanCreateDto;
import com.minhaacademiaonline.api.domain.dtos.TenantCreateDto;
import com.minhaacademiaonline.api.domain.entities.Plan;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService _service;

    @PostMapping
    public Plan create(@RequestBody PlanCreateDto req) {
        return _service.create(req);
    }

    @GetMapping
    public List<Plan> findAll() {
        return _service.findAll();
    }
}
