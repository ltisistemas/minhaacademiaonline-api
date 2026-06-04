package com.minhaacademiaonline.api.domain.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record AuthRegisterRequestDto(
        UUID selectedPlan,
        BigDecimal fee,

        String legalName,
        String tradeName,
        String nif,

        String userName,
        String userEmail,
        String userPassword
) {
}
