package com.backend.iLearn.modules.chat.repository;

import com.backend.iLearn.modules.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}
