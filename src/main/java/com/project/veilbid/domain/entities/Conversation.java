package com.project.veilbid.domain.entities;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(
        name = "conversations",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"lot_id", "participant_one_id", "participant_two_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "participant_one_id")
    private User participantOne;

    @ManyToOne
    @JoinColumn(name = "participant_two_id")
    private User participantTwo;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot lot;

    @CreatedDate
    private LocalDateTime createdAt;
}