package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentFindByIdResponse;
import com.minhaacademiaonline.api.application.interfaces.IStudentService;
import com.minhaacademiaonline.api.domain.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth/student")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService _service;

    @GetMapping("{id}")
    public StudentFindByIdResponse findStudentById(@PathVariable UUID id) {
        return _service.findStudentById(id);
    }

    @PostMapping("create-student-by-teacher")
    public ResponseEntity<StudentCreateResponseDto> createStudentByTeacher(@RequestBody StudentCreateRequestDto req) {
        return ResponseEntity.ok(_service.create(req, Student.StudentStatus.ACTIVE));
    }
}
