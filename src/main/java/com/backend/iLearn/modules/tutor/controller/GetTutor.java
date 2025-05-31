package com.backend.iLearn.modules.tutor.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.course.dto.CourseDto;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import com.backend.iLearn.modules.tutor.service.GetTutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tutor/fetch")
@RequiredArgsConstructor
public class GetTutor {
    private final GetTutorService getTutorService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse< TutorDto>> init(@PathVariable(value = "id") @Valid UUID id){
        TutorDto response = this.getTutorService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Tutor fetched successfully", response), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Set<TutorDto>>> init(@ModelAttribute TutorDto filter, @ModelAttribute PaginationRequest pageData){
        Set< TutorDto> response = this.getTutorService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("Tutors fetched successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{tutorId}/courses")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getTutorCourses(@Valid @PathVariable String tutorId){
        var response = this.getTutorService.getTutorCourses(tutorId);

        return new ResponseEntity<>(new ApiResponse<>("Success", response), HttpStatus.OK);
    }
}
