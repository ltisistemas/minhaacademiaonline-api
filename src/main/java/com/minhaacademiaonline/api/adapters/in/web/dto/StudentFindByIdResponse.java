package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record StudentFindByIdResponse(
        UUID id,
        String name,
        String nickname,
        String username,
        String phonenumber,
        String status,
        BeltDto belt,
        Set<GraduationHistoryDto> graduationHistories,
        Set<AttendanceDto> attendances
) {
}
