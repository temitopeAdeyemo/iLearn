package com.backend.iLearn.modules.auth.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.dto.CreateAdminDto;
import com.backend.iLearn.modules.auth.dto.CreateUserDto;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.service.RegisterService;
import com.backend.iLearn.modules.student.dto.CreateStudentDto;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.tutor.dto.CreateTutorDto;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class Register {
    private final RegisterService registerService;

    @PostMapping("/register/admin")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(@Valid @RequestBody CreateAdminDto payload){
        var response = this.registerService.init(payload);

        return new ResponseEntity<>( new ApiResponse<>("User  registered successfully", response), HttpStatus.OK);
    }

    @PostMapping("/register/student")
    public ResponseEntity<ApiResponse<Object>> init(@Valid @RequestBody CreateStudentDto payload){
        var response = this.registerService.init(payload);

        return new ResponseEntity<>( new ApiResponse<>("User  registered successfully", response), HttpStatus.OK);
    }

    @PostMapping("/register/tutor")
    public ResponseEntity<ApiResponse<Object>> init(@Valid @RequestBody CreateTutorDto payload){
        var response = this.registerService.init(payload);

        return new ResponseEntity<>( new ApiResponse<>("User  registered successfully", response), HttpStatus.OK);
    }
}
