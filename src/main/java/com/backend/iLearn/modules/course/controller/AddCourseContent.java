package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.dto.CreateCourseContentDto;
import com.backend.iLearn.modules.course.dto.CreateCourseContentResponseDto;
import com.backend.iLearn.modules.course.service.CreateCourseContentService;
import com.backend.iLearn.modules.course.service.CreateCourseVideoContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course/content")
@RequiredArgsConstructor
public class AddCourseContent {
    private final CreateCourseContentService createCourseContentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateCourseContentResponseDto>> init(@RequestBody @Valid CreateCourseContentDto payload){
        var response = this.createCourseContentService.exec(payload);
        return new ResponseEntity<>( new ApiResponse<>("Course content added successfully", response), HttpStatus.CREATED);
    }
}
