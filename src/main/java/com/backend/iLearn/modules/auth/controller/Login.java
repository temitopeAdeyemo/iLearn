package com.backend.iLearn.modules.auth.controller;

import com.backend.iLearn.common.utils.ApiResponse;
import com.backend.iLearn.modules.auth.dto.AccessTokenDto;
import com.backend.iLearn.modules.admin.dto.AdminLoginDto;
import com.backend.iLearn.modules.auth.service.LoginService;
import com.backend.iLearn.modules.student.dto.StudentLoginDto;
import com.backend.iLearn.modules.tutor.dto.TutorLoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class Login {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AccessTokenDto>> init(@Valid AdminLoginDto payload){
        AccessTokenDto response = this.loginService.exec(payload);

        return new ResponseEntity<>( new ApiResponse<>("User logged in successfully", response), HttpStatus.OK);
    }
}
