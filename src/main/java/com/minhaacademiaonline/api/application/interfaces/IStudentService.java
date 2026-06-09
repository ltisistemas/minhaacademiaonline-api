package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;

public interface IStudentService {
    StudentCreateResponseDto create(StudentCreateRequestDto req);
}
