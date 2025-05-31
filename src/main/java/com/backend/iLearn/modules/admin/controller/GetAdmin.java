package com.backend.iLearn.modules.admin.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.service.GetAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class GetAdmin {
    private final GetAdminService getAdminService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminDto>> init(@PathVariable(value = "id") @Valid UUID id){
        AdminDto response = this.getAdminService.getOne(id);
        return new ResponseEntity<>( new ApiResponse<>("User logged in successfully", response), HttpStatus.OK);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<ApiResponse<HashSet<AdminDto>>> init(@ModelAttribute AdminDto filter, @ModelAttribute PaginationRequest pageData){
        HashSet<AdminDto> response = this.getAdminService.getMany(filter, pageData);
        return new ResponseEntity<>( new ApiResponse<>("User logged in successfully", response), HttpStatus.OK);
    }
}
