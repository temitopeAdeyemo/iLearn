package com.backend.iLearn.modules.chat.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChatDto {
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Tutor Sender Id must be type uuid.")
    private String tutorSenderId;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Student Sender Id must be type uuid.")
    private String studentSenderId;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Admin Sender Id must be type uuid.")
    private String adminSenderId;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Tutor Receiver Id must be type uuid.")
    private String tutorReceiverId;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Student Receiver Id must be type uuid.")
    private String studentReceiverId;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Admin Receiver Id must be type uuid.")
    private String adminReceiverId;
}
