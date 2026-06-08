package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.adapters.in.web.dto.AuthSignUpRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.AuthSignUpResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.AuthSignInRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.AuthSignInResponseDto;

public interface IAuthService {
    AuthSignUpResponseDto signUp(AuthSignUpRequestDto req);
    AuthSignInResponseDto signIn(AuthSignInRequestDto req);
}
