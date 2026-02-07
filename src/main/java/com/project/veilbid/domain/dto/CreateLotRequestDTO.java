package com.project.veilbid.domain.dto;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLotRequestDTO {
    @NotBlank(message = "Название лота необходимо.")
    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private LotStatus status;
    private LotType lotType;
    private String imageUrl;
    @PositiveOrZero(message = "Цена должна быть больше нуля.")
    private BigDecimal startingPrice;
    private BigDecimal currentPrice;
    private String location;
}
