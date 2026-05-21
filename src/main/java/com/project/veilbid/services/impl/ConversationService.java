package com.project.veilbid.services.impl;

import com.project.veilbid.domain.entities.Conversation;
import com.project.veilbid.repositories.ConversationRepository;
import com.project.veilbid.repositories.LotRepository;
import com.project.veilbid.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        UUID p1 = userA.compareTo(userB) < 0
                ? userA
                : userB;

        UUID p2 = userA.compareTo(userB) < 0
                ? userB
                : userA;

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

    public Conversation getEntity(UUID id) {

        return conversationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Conversation not found"));
    }

    public ConversationDTO get(UUID id) {

        return toDto(getEntity(id));
    }
}