package com.backend.iLearn.modules.tutor.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.tutor.service.DeleteTutorProfileService;
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
@RequestMapping("/api/v1/tutor")
@RequiredArgsConstructor
public class DeleteTutorProfile {
    private final DeleteTutorProfileService deleteTutorProfile;

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse<Object>> init(@PathVariable(value = "user_id") @Valid UUID id){
        this.deleteTutorProfile.exec(id);
        return new ResponseEntity<>( new ApiResponse<>("Tutor Profile deleted successfully.", null), HttpStatus.CREATED);
    }
}
