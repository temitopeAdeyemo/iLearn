package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.utils.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.admin.dto.GetAdminByUniqueFieldDto;
import com.backend.iLearn.modules.course.dto.CourseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class GetCourse {
    @PostMapping("/fetch-one")
    public ResponseEntity<ApiResponse<Object>> init(@PathVariable @Valid GetAdminByUniqueFieldDto payload){
        return new ResponseEntity<>( new ApiResponse<>("Course fetched in successfully", null), HttpStatus.OK);
    }

    @PostMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Object>> init(@ModelAttribute CourseDto filter, @ModelAttribute PaginationRequest pageData){
        return new ResponseEntity<>( new ApiResponse<>("Courses fetched in successfully", null), HttpStatus.OK);
    }
}
