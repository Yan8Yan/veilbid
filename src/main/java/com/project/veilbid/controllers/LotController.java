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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @PostMapping
    public ResponseEntity<CreateLotResponseDTO> createLot(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateLotRequestDTO createLotRequestDTO){
        CreateLotRequest createLotRequest = lotMapper.fromDTO(createLotRequestDTO);
        UUID userId = UUID.fromString(jwt.getSubject());
        Lot createdLot =  lotService.createLotRequest(userId, createLotRequest);
        CreateLotResponseDTO createLotResponseDTO = lotMapper.toDTO(createdLot);
        return new ResponseEntity<>(createLotResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateLotResponseDTO>
    getLotById(@PathVariable UUID id) {

        recommendationService.trackGlobalLot(id);

        Lot lot = lotService.findById(id);

        return ResponseEntity.ok(
                lotMapper.toDTO(lot)
        );
    }

    @GetMapping
    public ResponseEntity<List<CreateLotResponseDTO>> getAllLots(
            @RequestParam(required = false) String lotType
    ) {
        List<Lot> lots = lotService.getAllLots(lotType);

        List<CreateLotResponseDTO> dtoList = lots.stream()
                .map(lotMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}
