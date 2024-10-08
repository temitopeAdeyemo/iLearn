package com.backend.iLearn.modules.tutor.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.tutor.dto.CreateTutorDto;
import com.backend.iLearn.modules.tutor.service.AddTutorProfileService;
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
@RequestMapping("/api/v1/tutor")
@RequiredArgsConstructor
public class AddTutorProfile {
    private final AddTutorProfileService addTutorProfileService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(){
        var authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authenticatedUser.getPrincipal();

        IdResponseDto response = this.addTutorProfileService.exec(user);
        return new ResponseEntity<>( new ApiResponse<>("Tutor profile added successfully.", response), HttpStatus.CREATED);
    }
}