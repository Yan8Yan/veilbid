package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.MessageDTO;
import com.project.veilbid.domain.dto.SendMessageRequest;
import com.project.veilbid.services.impl.MessageService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public MessageDTO send(
            @RequestBody SendMessageRequest request
    ) {

        return messageService.sendMessage(
                request.getConversationId(),
                request.getSenderId(),
                request.getText()
        );
    }

    @GetMapping("/{conversationId}")
    public List<MessageDTO> getMessages(
            @PathVariable UUID conversationId
    ) {

        return messageService.getMessages(conversationId);
    }
}