package com.backend.iLearn.modules.tutor.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import com.backend.iLearn.modules.tutor.service.GetTutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tutor")
@RequiredArgsConstructor
public class GetTutor {
    private final GetTutorService getTutorService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse< TutorDto>> init(@PathVariable(value = "id") @Valid UUID id){
        TutorDto response = this.getTutorService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Tutor fetched successfully", response), HttpStatus.OK);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Set<TutorDto>>> init(@ModelAttribute TutorDto filter, @ModelAttribute PaginationRequest pageData){
        Set< TutorDto> response = this.getTutorService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("Tutors fetched successfully", response), HttpStatus.OK);
    }
}
