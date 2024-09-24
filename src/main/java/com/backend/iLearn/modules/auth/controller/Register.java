package com.backend.iLearn.modules.auth.controller;

import com.backend.iLearn.common.utils.ApiResponse;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.auth.service.RegisterService;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class Register {
    private final RegisterService registerService;

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<Object>> init(@Valid AdminDto payload){
        this.registerService.init(payload);

        return new ResponseEntity<>( new ApiResponse<>("User  registered successfully", null), HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<ApiResponse<Object>> init(@Valid StudentDto payload){
        this.registerService.init(payload);

        return new ResponseEntity<>( new ApiResponse<>("User  registered successfully", null), HttpStatus.OK);
    }

    @PostMapping("/tutor")
    public ResponseEntity<ApiResponse<Object>> init(@Valid TutorDto payload){
        this.registerService.init(payload);

        return new ResponseEntity<>( new ApiResponse<>("User  registered successfully", null), HttpStatus.OK);
    }
}
