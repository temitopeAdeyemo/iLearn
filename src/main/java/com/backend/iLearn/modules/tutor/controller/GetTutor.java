package com.backend.iLearn.modules.tutor.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import com.backend.iLearn.modules.tutor.service.GetTutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/tutor")
@RequiredArgsConstructor
public class GetTutor {
    public GetTutorService getTutorService;
    @PostMapping("/fetch-one/{id}")
    public ResponseEntity<ApiResponse< Tutor>> init(@PathVariable(value = "id") String id){
        Tutor response = this.getTutorService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("Tutor fetched successfully", response), HttpStatus.OK);
    }

    @PostMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Set<Tutor>>> init(@ModelAttribute TutorDto filter, @ModelAttribute PaginationRequest pageData){
        Set< Tutor> response = this.getTutorService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("Tutors fetched successfully", response), HttpStatus.OK);
    }
}
