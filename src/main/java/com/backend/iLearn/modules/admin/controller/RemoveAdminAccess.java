package com.backend.iLearn.modules.admin.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.admin.service.RemoveAdminAccessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class RemoveAdminAccess {
    private final RemoveAdminAccessService removeAdminAccessService;

    @DeleteMapping("/access/{user_id}")
    public ResponseEntity<ApiResponse<Object>> init(@PathVariable(value = "user_id") @Valid UUID id){
        this.removeAdminAccessService.exec(id);
        return new ResponseEntity<>( new ApiResponse<>("Admin access removed successfully", null), HttpStatus.CREATED);
    }
}
