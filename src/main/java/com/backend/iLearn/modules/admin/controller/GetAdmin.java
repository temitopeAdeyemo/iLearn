package com.backend.iLearn.modules.admin.controller;

import com.backend.iLearn.common.utils.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.dto.GetAdminByUniqueFieldDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class GetAdmin {
    @PostMapping("/fetch-one")
    public ResponseEntity<ApiResponse<Object>> init(@PathVariable @Valid GetAdminByUniqueFieldDto payload){
        return new ResponseEntity<>( new ApiResponse<>("User logged in successfully", null), HttpStatus.OK);
    }

    @PostMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Object>> init(@ModelAttribute AdminDto filter, @ModelAttribute PaginationRequest pageData){
        return new ResponseEntity<>( new ApiResponse<>("User logged in successfully", null), HttpStatus.OK);
    }
}
