package com.project.veilbid.repositories;

import com.project.veilbid.domain.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    @Query("""
        SELECT c FROM Conversation c
        WHERE c.lot.id = :lotId
        AND c.participantOne.id = :p1
        AND c.participantTwo.id = :p2
    """)
    Optional<Conversation> findConversation(
            UUID lotId,
            UUID p1,
            UUID p2
    );
}