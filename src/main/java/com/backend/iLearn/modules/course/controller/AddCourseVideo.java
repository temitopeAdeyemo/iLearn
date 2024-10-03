package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseVideoDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.entity.CourseVideos;
import com.backend.iLearn.modules.course.service.CreateCourseVideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/video/course")
@RequiredArgsConstructor
public class AddCourseVideo {
    public CreateCourseVideoService createCourseVideoService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<IdResponseDto>> init(@RequestBody @Valid CreateCourseVideoDto payload){
        IdResponseDto response = this.createCourseVideoService.exec(payload);
        return new ResponseEntity<>( new ApiResponse<>("Course video added successfully", response), HttpStatus.CREATED);
    }
}
