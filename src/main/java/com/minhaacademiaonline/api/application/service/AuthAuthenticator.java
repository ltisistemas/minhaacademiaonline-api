package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.exceptions.AuthSignException;
import com.minhaacademiaonline.api.application.exceptions.TenantNotFoundException;
import com.minhaacademiaonline.api.application.interfaces.ITenantService;
import com.minhaacademiaonline.api.adapters.in.web.dto.AuthResult;
import com.minhaacademiaonline.api.adapters.in.web.dto.AuthSignInRequestDto;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthAuthenticator {
    private final ITenantService _service;
    private final UserService _userService;
    private final PasswordEncoder _pasPasswordEncoder;

    public AuthResult authenticate(AuthSignInRequestDto req) {
        Tenant tenant = Optional
                .ofNullable(_service.findTenantBySubdomain(req.subdomain()))
                .orElseThrow(() -> new TenantNotFoundException("Tenant not Found"));

        User user = _userService.loadUserByUsername(req.username());

        if (user == null || !_pasPasswordEncoder.matches(req.password(), user.getPassword())) {
            throw new AuthSignException("Invalid username or password");
        }

        return new AuthResult(tenant, user);
    }
}
