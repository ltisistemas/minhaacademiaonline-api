package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.application.service.PlanService;
import com.minhaacademiaonline.api.adapters.in.web.dto.PlanCreateDto;
import com.minhaacademiaonline.api.domain.entities.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/plans")
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
