package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.CreateLotRequestDTO;
import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.requests.CreateLotRequest;
import com.project.veilbid.mappers.LotMapper;
import com.project.veilbid.services.LotService;
import com.project.veilbid.services.RecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lots")
@RequiredArgsConstructor
public class LotController {

    private final LotMapper lotMapper;
    private final LotService lotService;
    private final RecommendationService recommendationService;

    // CREATE LOT (через seller из JWT делаем в другом контроллере)
    @PostMapping
    public ResponseEntity<CreateLotResponseDTO> createLot(
            @RequestParam UUID sellerId,
            @Valid @RequestBody CreateLotRequestDTO dto
    ) {
        CreateLotRequest request = lotMapper.fromDTO(dto);

        Lot created = lotService.createLotRequest(sellerId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(lotMapper.toDTO(created));
    }

    // GET BY ID (и трекинг популярности Redis)
    @GetMapping("/{id}")
    public ResponseEntity<CreateLotResponseDTO> getLotById(
            @PathVariable UUID id
    ) {
        recommendationService.trackGlobalLot(id);

        Lot lot = lotService.findById(id);

        return ResponseEntity.ok(lotMapper.toDTO(lot));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CreateLotResponseDTO>> getAllLots(
            @RequestParam(required = false) String lotType
    ) {
        List<CreateLotResponseDTO> result = lotService.getAllLots(lotType)
                .stream()
                .map(lotMapper::toDTO)
                .toList();

        return ResponseEntity.ok(result);
    }
}