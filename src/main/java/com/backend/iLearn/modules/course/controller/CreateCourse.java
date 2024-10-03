package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.service.CreateCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CreateCourse {
    public CreateCourseService createCourseService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(@RequestBody @Valid CreateCourseDto payload){
        IdResponseDto response = this.createCourseService.exec(payload);
        return new ResponseEntity<>( new ApiResponse<>("Course created in successfully", response), HttpStatus.CREATED);
    }
}
