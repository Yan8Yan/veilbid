package com.project.veilbid.services.impl;

import com.project.veilbid.domain.dto.MessageDTO;
import com.project.veilbid.domain.entities.Conversation;
import com.project.veilbid.domain.entities.Message;
import com.project.veilbid.domain.entities.User;
import com.project.veilbid.repositories.MessageRepository;
import com.project.veilbid.repositories.UserRepository;
import com.project.veilbid.services.impl.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final UserRepository userRepository;

    private MessageDTO toDto(Message message) {

        return new MessageDTO(
                message.getId(),
                message.getSender().getId(),
                message.getText(),
                message.getSentAt()
        );
    }

    @Transactional
    public MessageDTO sendMessage(
            UUID conversationId,
            UUID senderId,
            String text
    ) {

        Conversation conversation =
                conversationService.getEntity(conversationId);

        User sender =
                userRepository.getReferenceById(senderId);

        Message message = new Message();

        message.setConversation(conversation);
        message.setSender(sender);
        message.setText(text);
        message.setSentAt(LocalDateTime.now());

        Message saved =
                messageRepository.save(message);

        return toDto(saved);
    }

    public List<MessageDTO> getMessages(UUID conversationId) {

        return messageRepository
                .findByConversationIdOrderBySentAtAsc(conversationId)
                .stream()
                .map(this::toDto)
                .toList();
    }
}