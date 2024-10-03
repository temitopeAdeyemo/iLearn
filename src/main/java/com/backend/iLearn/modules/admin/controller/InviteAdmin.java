package com.backend.iLearn.modules.admin.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.dto.CreateAdminDto;
import com.backend.iLearn.modules.admin.service.CreateAdminService;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.course.dto.CreateCourseVideoDto;
import com.backend.iLearn.modules.course.service.CreateCourseVideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class InviteAdmin {
    private final CreateAdminService createAdminService;

    @PostMapping("/access")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(@RequestBody @Valid CreateAdminDto payload){
        var authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authenticatedUser.getPrincipal();

        IdResponseDto response = this.createAdminService.exec(payload, user);
        return new ResponseEntity<>( new ApiResponse<>("Admin access added successfully", response), HttpStatus.CREATED);
    }
}