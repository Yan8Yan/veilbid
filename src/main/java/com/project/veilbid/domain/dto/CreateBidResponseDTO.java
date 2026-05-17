package com.project.veilbid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBidResponseDTO {

    private UUID id;
    private BigDecimal amount;
    private UUID lotId;
    private UUID userId;
    private LocalDateTime createdAt;
}