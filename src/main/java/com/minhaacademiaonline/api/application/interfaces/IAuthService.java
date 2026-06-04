package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.domain.dtos.AuthSignUpRequestDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignUpResponseDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignInRequestDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignInResponseDto;

public interface IAuthService {
    AuthSignUpResponseDto signUp(AuthSignUpRequestDto req);
    AuthSignInResponseDto signIn(AuthSignInRequestDto req);
}
