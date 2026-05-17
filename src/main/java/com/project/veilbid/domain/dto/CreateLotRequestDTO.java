package com.project.veilbid.domain.dto;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLotRequestDTO {

    @NotBlank(message = "Название лота необходимо.")
    private String title;

    @NotBlank(message = "Описание обязательно.")
    private String description;

    @NotNull(message = "Дата начала обязательна.")
    private LocalDateTime startTime;

    @NotNull(message = "Дата окончания обязательна.")
    private LocalDateTime endTime;

    @NotNull(message = "Статус обязателен.")
    private LotStatus status;

    @NotNull(message = "Тип лота обязателен.")
    private LotType lotType;

    private String imageUrl;

    @NotNull
    @PositiveOrZero(message = "Цена должна быть больше нуля.")
    private BigDecimal startingPrice;

    @NotBlank(message = "Локация обязательна.")
    private String location;
}