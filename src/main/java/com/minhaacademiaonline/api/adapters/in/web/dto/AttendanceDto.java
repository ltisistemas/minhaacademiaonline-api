package com.minhaacademiaonline.api.adapters.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AttendanceDto(
        UUID id,
        LocalDateTime dueDate,
        String status
) {}
