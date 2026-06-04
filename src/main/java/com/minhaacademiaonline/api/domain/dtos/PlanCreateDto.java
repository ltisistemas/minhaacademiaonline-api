package com.minhaacademiaonline.api.domain.dtos;

import java.math.BigDecimal;

public record PlanCreateDto(String name, String content, Long maxStudents, BigDecimal fee) {
}
