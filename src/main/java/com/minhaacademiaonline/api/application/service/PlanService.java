package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.dtos.PlanCreateDto;
import com.minhaacademiaonline.api.domain.entities.Plan;
import com.minhaacademiaonline.api.infra.repositories.PlanRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository _repository;

    public Plan create(PlanCreateDto req) {
        Plan plan = new Plan();
        plan.setName(req.name());
        plan.setContent(req.content());
        plan.setMaxStudents(req.maxStudents());
        plan.setFee(req.fee());
        return _repository.save(plan);
    }

    public List<Plan> findAll() {
        return _repository.findAll();
    }

    public @Nullable  Plan findById(UUID id) {
        return _repository.findPlanById(id).orElse(null);
    }
}
