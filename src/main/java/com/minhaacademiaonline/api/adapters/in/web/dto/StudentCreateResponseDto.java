package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.Belt;
import com.minhaacademiaonline.api.domain.entities.Student;

import java.time.LocalDate;
import java.util.UUID;

public record StudentCreateResponseDto(
        UUID sub,
        Belt belt,
        String name,
        String nickname,
        String username,
        String phoneNumber,
        Student.StudentStatus status,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
