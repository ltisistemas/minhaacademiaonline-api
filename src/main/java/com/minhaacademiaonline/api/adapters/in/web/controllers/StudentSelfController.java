package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.StudentCreateResponseDto;
import com.minhaacademiaonline.api.application.interfaces.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student-self")
@RequiredArgsConstructor
public class StudentSelfController {
    private final IStudentService _service;

    @PostMapping("sing-up")
    public ResponseEntity<StudentCreateResponseDto> signUp(@RequestBody StudentCreateRequestDto req) {
        return ResponseEntity.ok(_service.create(req));
    }
}
