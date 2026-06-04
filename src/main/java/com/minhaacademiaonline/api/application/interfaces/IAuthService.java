package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.domain.dtos.AuthRegisterRequestDto;
import com.minhaacademiaonline.api.domain.dtos.AuthRegisterResponseDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignInRequestDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignInResponseDto;

public interface IAuthService {
    AuthRegisterResponseDto signUp(AuthRegisterRequestDto req);
    AuthSignInResponseDto signIn(AuthSignInRequestDto req);
}
