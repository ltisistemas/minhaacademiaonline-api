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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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
    @Transactional
    public StudentCreateResponseDto create(StudentCreateRequestDto req, Student.StudentStatus status) {
        try {
            Tenant tenant = _tenantService.findTenantBySubdomain(req.subdomain());
            Belt belt = _beltService.findById(req.beltId());

            if (tenant == null) throw new TenantNotFoundException("Tenant not found");
            if (belt == null) throw new BeltNotFoundException("Belt not found");

            Student studentSearch = findStudentByUsername(req.username());
            if (studentSearch != null) {
                throw new StudentCreateException("Student already exists", null);
            }

            Student sts = Student
                    .builder()
                    .name(req.name())
                    .belt(belt)
                    .phonenumber(req.phoneNumber())
                    .nickname(req.nickname())
                    .username(req.username())
                    .password(_PasswordEncoder.encode(req.password()))
                    .status(status)
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
        } catch (RuntimeException e) {
            throw new StudentCreateException(e.getMessage(), e);
        }
    }

    public @Nullable Student findStudentByUsername(String userName) {
        return _repository.findStudentByUsername(userName).orElse(null);
    }

    @Nullable
    @Override
    @Transactional(readOnly = true)
    public StudentFindByIdResponse findStudentById(UUID id) {
        // Primeira query: Resolve Student, Belt e histórico de graduação
        Student student = _repository.findByIdWithBeltAndHistory(id).orElse(null);

        if (student != null) {
            // Segunda query: Alimenta o Persistence Context com as presenças (frequência) na mesma sessão
            _repository.findByIdWithAttendances(id);
        }

        return _mapper.toStudentFindByIdResponse(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentFindByIdResponse> findAllByTenantId(UUID tenantId) {
        Tenant tenant = _tenantService.findById(tenantId);
        if (tenant == null) {
            throw new TenantNotFoundException("Tenant not found");
        }

        List<Student> students = _repository.findAllByTenantIdWithBeltAndHistory(tenantId);

        if (students.isEmpty()) {
            return Collections.emptyList();
        }

        // Alimenta o Persistence Context com as presenças (frequência) para todos os alunos em uma única query
        List<UUID> studentIds = students.stream().map(Student::getId).toList();
        _repository.findAllByIdInWithAttendances(studentIds);

        return students.stream()
                .map(_mapper::toStudentFindByIdResponse)
                .toList();
    }
}
