package com.project.veilbid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MessageDTO {

    private UUID id;
    private UUID senderId;
    private String text;
    private LocalDateTime sentAt;
}