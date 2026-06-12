package com.minhaacademiaonline.api.application.mappers;

import com.minhaacademiaonline.api.adapters.in.web.dto.*;
import com.minhaacademiaonline.api.domain.entities.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "sub", source = "id")
    @Mapping(target = "phoneNumber", source = "phonenumber")
    StudentCreateResponseDto toStudentCreateDtoResponse(Student student);

    // Assumindo que o target no DTO foi definido como 'id' e 'phonenumber' (ou 'phoneNumber')
    @Mapping(target = "id", source = "id") // Troque para "sub" se o record utilizar "sub"
    @Mapping(target = "phonenumber", source = "phonenumber")
    StudentFindByIdResponse toStudentFindByIdResponse(Student student);

    // Sub-mapeamentos para objetos aninhados e coleções
    BeltDto toBeltDto(Belt belt);

    @Mapping(target = "beltId", source = "belt.id")
    @Mapping(target = "beltName", source = "belt.name")
    GraduationHistoryDto toGraduationHistoryDto(GraduationHistory history);

    AttendanceDto toAttendanceDto(Attendance attendance);
}
