package com.backend.iLearn.modules.student.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.student.service.DeleteStudentProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class DeleteStudentProfile {
    private final DeleteStudentProfileService deleteStudentProfileService;

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse<Object>> init(@PathVariable(value = "user_id") @Valid UUID id){
        this.deleteStudentProfileService.exec(id);
        return new ResponseEntity<>( new ApiResponse<>("Student profile deleted successfully.", null), HttpStatus.CREATED);
    }
}
