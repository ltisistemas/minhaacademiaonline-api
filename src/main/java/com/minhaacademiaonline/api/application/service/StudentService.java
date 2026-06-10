package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentFindByIdResponse;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentTenantCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.BeltNotFoundException;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.StudentCreateException;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.TenantNotFoundException;
import com.minhaacademiaonline.api.application.interfaces.IBeltService;
import com.minhaacademiaonline.api.application.interfaces.IStudentService;
import com.minhaacademiaonline.api.application.interfaces.IStudentTenantService;
import com.minhaacademiaonline.api.application.mappers.StudentMapper;
import com.minhaacademiaonline.api.domain.entities.Belt;
import com.minhaacademiaonline.api.domain.entities.Student;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.infra.repositories.StudentRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository _repository;
    private final IBeltService _beltService;
    private final TenantService _tenantService;
    private final IStudentTenantService _studentTenantService;
    private final StudentMapper _mapper;
    private final PasswordEncoder _PasswordEncoder;

    @Override
    public StudentCreateResponseDto create(StudentCreateRequestDto req) {
        try {
            Tenant tenant = _tenantService.findTenantBySubdomain(req.subdomain());
            Belt belt = _beltService.findById(req.beltId());

            if (tenant == null) throw new TenantNotFoundException("Tenant not found");
            if (belt == null) throw new BeltNotFoundException("Belt not found");

            Student studentSearch = findStudentByUsername(req.username());
            if (studentSearch != null) {
                throw new StudentCreateException("Student already exists");
            }

            Student sts = Student
                    .builder()
                    .name(req.name())
                    .belt(belt)
                    .phonenumber(req.phoneNumber())
                    .nickname(req.nickname())
                    .username(req.username())
                    .password(_PasswordEncoder.encode(req.password()))
                    .build();

            Student student = _repository.save(sts);

            _studentTenantService.create(
                    new StudentTenantCreateRequestDto(
                        tenant,
                        student,
                        LocalDateTime.now()
                    )
            );

            return _mapper.toStudentCreateDtoResponse(student);
        } catch (Exception e) {
            throw new StudentCreateException(e.getMessage());
        }
    }

    public @Nullable Student findStudentByUsername(String userName) {
        return _repository.findStudentByUsername(userName).orElse(null);
    }

    @Nullable
    @Override
    public StudentFindByIdResponse findStudentById(UUID id) {
//        Student student = _repository.findStudentById(id).orElse(null);
//        if (student != null) {
//            Belt belt =
//        }

        return _mapper.toStudentFindByIdResponse(_repository.findStudentById(id).orElse(null));
    }
}
