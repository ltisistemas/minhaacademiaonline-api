package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    User findUsersById(UUID id);
}
