package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.domain.dtos.AuthRegisterResponseDto;

import java.util.UUID;

public interface ITokenService {
    String generateToken(UUID tenantId, UUID sub, String subdomain, String userName);
}
