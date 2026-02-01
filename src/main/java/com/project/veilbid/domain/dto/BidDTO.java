package com.project.veilbid.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BidDTO(
        UUID id,
        LocalDateTime created,
        Double amount
) {
}
