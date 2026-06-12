package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.Student;
import com.minhaacademiaonline.api.domain.entities.Tenant;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentTenantCreateRequestDto(
        Tenant tenant,
        Student student,
        LocalDateTime enrolledAt
) {
}
