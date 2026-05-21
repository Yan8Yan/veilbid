package com.project.veilbid.domain.dto;

import lombok.Data;

import java.util.UUID;

import lombok.Data;

import java.util.UUID;

@Data
public class SendMessageRequest {

    private UUID conversationId;

    private UUID senderId;

    private String text;
}