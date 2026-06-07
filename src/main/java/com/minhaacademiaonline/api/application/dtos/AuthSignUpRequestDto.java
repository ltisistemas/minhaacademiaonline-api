package com.minhaacademiaonline.api.application.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record AuthSignUpRequestDto(
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
