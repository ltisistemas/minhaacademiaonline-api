package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.dtos.UserCreateRequestDto;
import com.minhaacademiaonline.api.domain.entities.User;
import com.minhaacademiaonline.api.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService  {
    private final UserRepository _repository;
    private final PasswordEncoder _passwordEncoder;

    @Override
    public @Nullable User loadUserByUsername(String username) throws UsernameNotFoundException {
        return _repository.findByUsername(username)
                .orElse(null);
        //ElseThrow(() -> new UsernameNotFoundException("User not found: " + userEmail));
    }

    public User findUserById(UUID id) {
        return _repository.findUsersById(id);
    }

    public User create(UserCreateRequestDto req) {
        User user = new User();
        user.setName(req.userName());
        user.setUsername(req.userEmail());
        user.setPassword(_passwordEncoder.encode(req.userPassword()));
        return _repository.save(user);
    }
}
