package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.application.interfaces.IBeltService;
import com.minhaacademiaonline.api.domain.entities.Belt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeltService implements IBeltService {
    private final BeltRepository _repository;

    @Override
    public Belt findById(UUID id) {
        return _repository.findBeltById(id).orElse(null);
    }
}
