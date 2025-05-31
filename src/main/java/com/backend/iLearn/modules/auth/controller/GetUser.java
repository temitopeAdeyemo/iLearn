package com.backend.iLearn.modules.auth.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class GetUser {
    private final GetUserService getUserService;
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> init(){
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var response = this.getUserService.findOne(user.getId());

        return new ResponseEntity<>( new ApiResponse<>("User fetched successfully", response), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserDto>>> findAll(){
        var response = this.getUserService.findAll();

        return new ResponseEntity<>( new ApiResponse<>("User fetched successfully", response), HttpStatus.OK);
    }
}
