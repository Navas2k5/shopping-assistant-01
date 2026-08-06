package com.shopai.shopping_assistant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String message;

    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt(){
        if(createdAt==null){
            createdAt=LocalDateTime.now();
        }
    }
}
