package com.minhaacademiaonline.api.adapters.in.web.dto;

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
