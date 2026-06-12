package com.minhaacademiaonline.api.application.interfaces;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentTenantCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentTenantCreateResponseDto;

public interface IStudentTenantService {
    StudentTenantCreateResponseDto create(StudentTenantCreateRequestDto req);
}
