package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentTenantCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentTenantCreateResponseDto;
import com.minhaacademiaonline.api.application.interfaces.IStudentTenantService;
import com.minhaacademiaonline.api.application.mappers.StudentTenantMapper;
import com.minhaacademiaonline.api.domain.entities.StudentTenant;
import com.minhaacademiaonline.api.infra.repositories.StudentTenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentTenantService implements IStudentTenantService {
    private final StudentTenantRepository _repository;
    private final StudentTenantMapper _mapper;

    public StudentTenantCreateResponseDto create(StudentTenantCreateRequestDto req) {
        StudentTenant studentTenant = StudentTenant
                .builder()
                .student(req.student())
                .tenant(req.tenant())
                .enrolledAt(req.enrolledAt())
                .build();

        return _mapper.toStudentTenantCreateDtoResponse(_repository.save(studentTenant));
    }
}
