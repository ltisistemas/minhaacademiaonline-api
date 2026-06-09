package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.domain.entities.Belt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeltRepository extends JpaRepository<Belt, UUID> {
    Optional<Belt> findBeltById(UUID id);
}
