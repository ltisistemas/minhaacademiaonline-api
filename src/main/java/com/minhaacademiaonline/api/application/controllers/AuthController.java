package com.minhaacademiaonline.api.application.controllers;

import com.minhaacademiaonline.api.application.interfaces.IAuthService;
import com.minhaacademiaonline.api.domain.dtos.AuthSignUpRequestDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignUpResponseDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignInRequestDto;
import com.minhaacademiaonline.api.domain.dtos.AuthSignInResponseDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService _service;

    @PostMapping("sign-up")
    @SecurityRequirements()
    public AuthSignUpResponseDto signUp(@RequestBody AuthSignUpRequestDto req) {
        return _service.signUp(req);
    }

    @PostMapping("sign-in")
    @SecurityRequirements()
    public AuthSignInResponseDto signUp(@RequestBody AuthSignInRequestDto req) {
        return _service.signIn(req);
    }
}
