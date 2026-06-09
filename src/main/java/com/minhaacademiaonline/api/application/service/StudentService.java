package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.StudentCreateException;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.TenantNotFoundException;
import com.minhaacademiaonline.api.application.interfaces.IStudentService;
import com.minhaacademiaonline.api.application.mappers.StudentMapper;
import com.minhaacademiaonline.api.domain.entities.Student;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.infra.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository _repository;
    private final TenantService _tenantService;
    private final StudentMapper _mapper;
    private final PasswordEncoder _PasswordEncoder;

    @Override
    public StudentCreateResponseDto create(StudentCreateRequestDto req) {
        try {
            Tenant tenant = _tenantService.findTenantBySubdomain(req.subdomain());
            if (tenant != null) {
                Student sts = Student
                        .builder()
                        .name(req.name())
                        .belt(req.belt())
                        .phonenumber(req.phoneNumber())
                        .nickname(req.nickname())
                        .username(req.username())
                        .password(_PasswordEncoder.encode(req.password()))
                        .build();

                return _mapper.toStudentCreateDtoResponse(_repository.save(sts));
            }

            throw new TenantNotFoundException("Tenant not found");
        } catch (Exception e) {
            throw new StudentCreateException(e.getMessage());
        }
    }
}
