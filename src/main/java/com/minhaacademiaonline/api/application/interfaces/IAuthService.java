package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.application.dtos.AuthSignUpRequestDto;
import com.minhaacademiaonline.api.application.dtos.AuthSignUpResponseDto;
import com.minhaacademiaonline.api.application.dtos.AuthSignInRequestDto;
import com.minhaacademiaonline.api.application.dtos.AuthSignInResponseDto;

public interface IAuthService {
    AuthSignUpResponseDto signUp(AuthSignUpRequestDto req);
    AuthSignInResponseDto signIn(AuthSignInRequestDto req);
}
