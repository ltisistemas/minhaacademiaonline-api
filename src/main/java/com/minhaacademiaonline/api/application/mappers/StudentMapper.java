package com.minhaacademiaonline.api.application.mappers;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.domain.entities.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "sub", source = "student.id")
    @Mapping(target = "belt", source = "student.belt")
    @Mapping(target = "name", source = "student.name")
    @Mapping(target = "nickname", source = "result.tenant.id")
    @Mapping(target = "phoneNumber", source = "student.phoneNumber")
    @Mapping(target = "status", source = "student.status")
    @Mapping(target = "createdAt", source = "student.createdAt")
    @Mapping(target = "updatedAt", source = "student.updatedAt")
    StudentCreateResponseDto toStudentCreateDtoResponse(Student student);
}
