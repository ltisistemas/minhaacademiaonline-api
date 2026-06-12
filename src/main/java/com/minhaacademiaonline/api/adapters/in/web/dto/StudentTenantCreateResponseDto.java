package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.Student;
import com.minhaacademiaonline.api.domain.entities.Tenant;

import java.time.LocalDate;
import java.util.UUID;

public record StudentTenantCreateResponseDto(
        UUID sub,
        Tenant tenant,
        Student student,
        LocalDate enrolledAt,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
