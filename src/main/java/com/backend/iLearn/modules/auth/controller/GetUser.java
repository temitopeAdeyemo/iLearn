package com.backend.iLearn.modules.auth.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.admin.dto.AdminLoginDto;
import com.backend.iLearn.modules.auth.dto.AccessTokenDto;
import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class GetUser {
    private final GetUserService getUserService;
    @GetMapping("/")
    public ResponseEntity<ApiResponse<UserDto>> init(){
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var response = this.getUserService.exec(user.getId());

        return new ResponseEntity<>( new ApiResponse<>("User fetched successfully", response), HttpStatus.OK);
    }
}
