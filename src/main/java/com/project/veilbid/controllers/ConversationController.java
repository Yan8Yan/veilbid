package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.ConversationDTO;
import com.project.veilbid.domain.dto.ConversationListItemDTO;
import com.project.veilbid.services.impl.ConversationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping("/start")
    public ConversationDTO createOrGet(
            @RequestParam UUID lotId,
            @RequestParam UUID userA,
            @RequestParam UUID userB
    ) {

        return conversationService.getOrCreate(
                lotId,
                userA,
                userB
        );
    }

    @GetMapping("/{id}")
    public ConversationDTO get(
            @PathVariable UUID id
    ) {

        return conversationService.get(id);
    }

    @GetMapping("/user/{userId}")
    public List<ConversationListItemDTO> getUserConversations(
            @PathVariable UUID userId
    ) {
        return conversationService.getUserConversations(userId);
    }
}