package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentFindByIdResponse;
import com.minhaacademiaonline.api.domain.entities.Student;
import jakarta.annotation.Nullable;

import java.util.UUID;

public interface IStudentService {
    StudentCreateResponseDto create(StudentCreateRequestDto req);

    @Nullable
    StudentFindByIdResponse findStudentById(UUID id);
}
