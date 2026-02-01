package com.project.veilbid.domain.dto;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record LotDTO(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    LotStatus status,
    LotType type,
    List<BidDTO> bids
    )
{

}
