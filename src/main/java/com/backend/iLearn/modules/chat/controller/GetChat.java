package com.backend.iLearn.modules.chat.controller;

import com.backend.iLearn.common.responses.ApiResponse;
import com.backend.iLearn.modules.chat.dto.ChatDto;
import com.backend.iLearn.modules.chat.entity.Chat;
import com.backend.iLearn.modules.chat.service.GetChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class GetChat {
    private final GetChatService getChatService;

    @GetMapping("/fetch-all")
    public ResponseEntity<ApiResponse<Set<Chat>>> init(@Valid @ModelAttribute ChatDto filter){
        var response = this.getChatService.getConversation();
        return new ResponseEntity<>( new ApiResponse<>("Chat fetched in successfully", response), HttpStatus.OK);
    }
}
