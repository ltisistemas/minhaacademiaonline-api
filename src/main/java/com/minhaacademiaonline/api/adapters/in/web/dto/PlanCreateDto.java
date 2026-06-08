package com.minhaacademiaonline.api.adapters.in.web.dto;

import java.math.BigDecimal;

public record PlanCreateDto(String name, String content, Long maxStudents, BigDecimal fee) {
}
