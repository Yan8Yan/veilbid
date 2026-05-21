package com.project.veilbid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ConversationDTO {

    private UUID id;

    private UUID participantOneId;

    private UUID participantTwoId;

    private UUID lotId;
}