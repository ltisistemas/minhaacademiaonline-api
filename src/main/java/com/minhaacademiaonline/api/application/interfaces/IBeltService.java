package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.domain.entities.Belt;

import java.util.UUID;

public interface IBeltService {
    Belt findById(UUID id);
}
