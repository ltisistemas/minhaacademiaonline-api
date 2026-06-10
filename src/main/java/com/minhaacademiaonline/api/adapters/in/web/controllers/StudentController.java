package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentFindByIdResponse;
import com.minhaacademiaonline.api.application.interfaces.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
