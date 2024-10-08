package com.backend.iLearn.modules.student.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.student.service.GetStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class GetStudent {
    public final GetStudentService getStudentService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse< StudentDto>> init(@PathVariable(value = "id") UUID id){
        var response = this.getStudentService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Student fetched in successfully", response), HttpStatus.OK);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Set<StudentDto>>> init(@ModelAttribute StudentDto filter, @ModelAttribute PaginationRequest pageData){
        Set< StudentDto> response = this.getStudentService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("Students fetched in successfully", response), HttpStatus.OK);
    }
}
