package com.backend.iLearn.modules.chat.controller;

import com.backend.iLearn.common.utils.ApiResponse;
import com.backend.iLearn.modules.chat.dto.ChatDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class GetChat {
    @PostMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Object>> init(@Valid @ModelAttribute ChatDto filter){
        return new ResponseEntity<>( new ApiResponse<>("Chat fetched in successfully", null), HttpStatus.OK);
    }
}
