package com.minhaacademiaonline.api.adapters.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GraduationHistoryDto(
        UUID id,
        UUID beltId,
        String beltName,
        Long countFrequence,
        LocalDateTime graduatedAt
) {}