package com.project.veilbid.services.impl;

import com.project.veilbid.domain.dto.ConversationListItemDTO;
import com.project.veilbid.domain.entities.Conversation;
import com.project.veilbid.domain.entities.Message;
import com.project.veilbid.domain.entities.User;
import com.project.veilbid.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.project.veilbid.domain.dto.ConversationDTO;
import com.project.veilbid.domain.entities.Conversation;
import com.project.veilbid.repositories.ConversationRepository;
import com.project.veilbid.repositories.LotRepository;
import com.project.veilbid.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final LotRepository lotRepository;

    private ConversationDTO toDto(Conversation c) {

        return new ConversationDTO(
                c.getId(),
                c.getParticipantOne().getId(),
                c.getParticipantTwo().getId(),
                c.getLot().getId()
        );
    }

    @Transactional
    public ConversationDTO getOrCreate(
            UUID lotId,
            UUID userA,
            UUID userB
    ) {

        UUID p1 = userA.compareTo(userB) < 0 ? userA : userB;
        UUID p2 = userA.compareTo(userB) < 0 ? userB : userA;

        Conversation conversation =
                conversationRepository
                        .findConversation(lotId, p1, p2)
                        .orElseGet(() -> {

                            Conversation c = new Conversation();

                            c.setLot(
                                    lotRepository.getReferenceById(lotId)
                            );

                            c.setParticipantOne(
                                    userRepository.getReferenceById(p1)
                            );

                            c.setParticipantTwo(
                                    userRepository.getReferenceById(p2)
                            );

                            return conversationRepository.save(c);
                        });

        return toDto(conversation);
    }

    // ---------------------------
    // GET entity (for messages)
    // ---------------------------
    public Conversation getEntity(UUID id) {

        return conversationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Conversation not found"));
    }

    // ---------------------------
    // GET single conversation DTO
    // ---------------------------
    public ConversationDTO get(UUID id) {

        return toDto(getEntity(id));
    }

    @Transactional(readOnly = true)
    public List<ConversationListItemDTO> getUserConversations(UUID userId) {

        List<Conversation> conversations =
                conversationRepository.findAllByUserId(userId);

        return conversations.stream()
                .map(c -> {

                    boolean isUserOne =
                            c.getParticipantOne().getId().equals(userId);

                    User otherUser =
                            isUserOne
                                    ? c.getParticipantTwo()
                                    : c.getParticipantOne();

                    Message lastMessage =
                            messageRepository
                                    .findFirstByConversationIdOrderBySentAtDesc(
                                            c.getId()
                                    )
                                    .orElse(null);

                    return new ConversationListItemDTO(
                            c.getId(),

                            c.getLot().getId(),
                            c.getLot().getTitle(),

                            otherUser.getId(),
                            otherUser.getName(),

                            lastMessage != null
                                    ? lastMessage.getText()
                                    : "",

                            lastMessage != null
                                    ? lastMessage.getSentAt()
                                    : null
                    );
                })
                .toList();
    }
}