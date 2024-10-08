package com.backend.iLearn.modules.student.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.student.dto.CreateStudentDto;
import com.backend.iLearn.modules.student.service.AddStudentProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class AddStudentProfile {
    private final AddStudentProfileService addStudentProfileService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(){
        var authUser = SecurityContextHolder.getContext().getAuthentication();

        var user = (User)authUser.getPrincipal();
        IdResponseDto response = this.addStudentProfileService.exec( user);
        return new ResponseEntity<>( new ApiResponse<>("Student profile added successfully", response), HttpStatus.CREATED);
    }
}