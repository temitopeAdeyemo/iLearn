package com.backend.iLearn.modules.student.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.admin.service.RemoveAdminAccessService;
import com.backend.iLearn.modules.tutor.service.DeleteTutorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class DeleteStudentProfile {
    public DeleteTutorProfileService deleteTutorProfileService;

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse<Object>> init(@PathVariable(value = "user_id") String id){
        this.deleteTutorProfileService.exec(id);
        return new ResponseEntity<>( new ApiResponse<>("Student profile deleted successfully.", null), HttpStatus.CREATED);
    }
}
