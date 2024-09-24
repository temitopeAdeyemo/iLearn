package com.backend.iLearn.modules.student.controller;

import com.backend.iLearn.common.utils.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.student.service.GetStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class GetStudent {
    public GetStudentService getStudentService;
    @PostMapping("/fetch-one/{id}")
    public ResponseEntity<ApiResponse< Student>> init(@PathVariable(value = "id") String id){
        Student response = this.getStudentService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Student fetched in successfully", response), HttpStatus.OK);
    }

    @PostMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Set<Student>>> init(@ModelAttribute StudentDto filter, @ModelAttribute PaginationRequest pageData){
        Set< Student> response = this.getStudentService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("Students fetched in successfully", response), HttpStatus.OK);
    }
}
