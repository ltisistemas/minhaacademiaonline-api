package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentFindByIdResponse;
import com.minhaacademiaonline.api.domain.entities.Student;
import jakarta.annotation.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    StudentCreateResponseDto create(StudentCreateRequestDto req, Student.StudentStatus status);

    @Nullable
    StudentFindByIdResponse findStudentById(UUID id);

    @Transactional(readOnly = true)
    List<StudentFindByIdResponse> findAllByTenantId(UUID tenantId);
}
