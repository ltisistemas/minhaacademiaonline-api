package com.minhaacademiaonline.api.application.mappers;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentTenantCreateResponseDto;
import com.minhaacademiaonline.api.domain.entities.Student;
import com.minhaacademiaonline.api.domain.entities.StudentTenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentTenantMapper {
    @Mapping(target = "sub", source = "studentTenant.id")
    @Mapping(target = "tenant", source = "studentTenant.tenant")
    @Mapping(target = "student", source = "studentTenant.student")
    @Mapping(target = "enrolledAt", source = "studentTenant.enrolledAt")
    @Mapping(target = "createdAt", source = "studentTenant.createdAt")
    @Mapping(target = "updatedAt", source = "studentTenant.updatedAt")
    StudentTenantCreateResponseDto toStudentTenantCreateDtoResponse(StudentTenant studentTenant);
}
