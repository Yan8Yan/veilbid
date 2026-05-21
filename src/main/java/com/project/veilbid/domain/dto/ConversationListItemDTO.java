package com.project.veilbid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
public class ConversationListItemDTO {

    private UUID conversationId;

    private UUID lotId;
    private String lotTitle;

    private UUID otherUserId;
    private String otherUserName;

    private String lastMessage;
    private LocalDateTime lastMessageAt;
}