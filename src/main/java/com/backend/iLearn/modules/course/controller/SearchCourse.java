package com.backend.iLearn.modules.course.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.course.dto.CourseDto;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.service.GetCourseService;
import com.backend.iLearn.modules.course.service.GetCourseVideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class SearchCourse {
    public GetCourseService getCourseService;
    @GetMapping("/{keyword}")
    public ResponseEntity<ApiResponse<HashSet<CourseDto>>> init(@PathVariable(value = "keyword") String keyword){
        HashSet<CourseDto> response = this.getCourseService.search(keyword);
        return new ResponseEntity<>( new ApiResponse<>("Course fetched in successfully", response), HttpStatus.OK);
    }
}
