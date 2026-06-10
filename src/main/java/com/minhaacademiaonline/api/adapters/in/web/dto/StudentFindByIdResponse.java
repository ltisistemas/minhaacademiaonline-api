package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record StudentFindByIdResponse(
        UUID sub,
        Tenant tenant,
        Student student,
        LocalDateTime enrolledAt,
        Student.StudentStatus status,
        LocalDate createdAt,
        LocalDate updatedAt,
        Belt belt,
        List<StudentBeltStatus> studentBeltStatus,
        List<GraduationHistory> graduationHistory,
        List<Attendance> attendance

) {
}
