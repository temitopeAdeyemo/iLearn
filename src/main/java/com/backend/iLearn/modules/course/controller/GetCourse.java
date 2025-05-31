package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.course.dto.CourseDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.service.GetCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class GetCourse {
    public final GetCourseService getCourseService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourse (@PathVariable @Valid String id){
        System.out.println("***********************:::");
        CourseDto response = this.getCourseService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Course fetched in successfully", response), HttpStatus.OK);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getAllCourses(@ModelAttribute CourseDto filter, @ModelAttribute PaginationRequest pageData){
        List<CourseDto> response = this.getCourseService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("Courses fetched in successfully", response), HttpStatus.OK);
    }
}
