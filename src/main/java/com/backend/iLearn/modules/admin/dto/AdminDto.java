package com.backend.iLearn.modules.admin.dto;

import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.chat.entity.Chat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminDto {
    private UUID id;

    private String firstName;

    private String lastName;

    private  String email;
//    private Set<Chat> sentChats;
//
//    private Set<Chat> receivedChats;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto user;

    private Date createdAt;

    private Date updatedAt;
}
