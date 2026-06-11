package com.minhaacademiaonline.api.adapters.in.web.dto;

import java.util.UUID;

public record BeltDto(
        UUID id,
        String name,
        Long minNumberOfYears
) {}