package com.backend.iLearn.modules.admin.dto;

import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.chat.entity.Chat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AdminDto {
    private UUID id;

    private String firstName;

    private String lastName;

    private Set<Chat> sentChats;

    private Set<Chat> receivedChats;

    private User user;

    private Date createdAt;

    private Date updatedAt;
}
