package com.shopai.shopping_assistant.repository;

import com.shopai.shopping_assistant.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    List<ChatMessage> findByUserIdOrderByCreatedAtAsc(Long userId);
    void deleteByUserId(Long userId);
    long countByUserId(Long userId);
}
