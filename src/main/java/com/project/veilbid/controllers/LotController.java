package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.CreateLotRequestDTO;
import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.domain.dto.UpdateLotRequestDTO;
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
            @Valid @RequestBody CreateLotRequestDTO dto) {

        UUID userId = UUID.fromString(jwt.getSubject());

        Lot createdLot = lotService.createLotRequest(
                userId,
                lotMapper.fromDTO(dto)
        );

        return new ResponseEntity<>(
                lotMapper.toDTO(createdLot),
                HttpStatus.CREATED
        );
    }

    // ✅ FIXED (без regex)
    @GetMapping("/{id}")
    public ResponseEntity<CreateLotResponseDTO> getLotById(@PathVariable UUID id) {

        recommendationService.trackGlobalLot(id);

        return ResponseEntity.ok(
                lotMapper.toDTO(lotService.findById(id))
        );
    }

    // ✅ ОДИН getAllLots (объединённый)
    @GetMapping
    public ResponseEntity<List<CreateLotResponseDTO>> getAllLots(
            @RequestParam(required = false) String lotType,
            @RequestParam(required = false) String search
    ) {

        List<Lot> lots = lotService.getAllLots(lotType, search);

        return ResponseEntity.ok(
                lots.stream()
                        .map(lotMapper::toDTO)
                        .toList()
        );
    }

    @GetMapping("/me")
    public ResponseEntity<List<CreateLotResponseDTO>> getMyLots(
            @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());

        return ResponseEntity.ok(
                lotService.getMyLots(userId)
                        .stream()
                        .map(lotMapper::toDTO)
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLot(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        lotService.deleteLot(userId, id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<Void> closeLot(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        lotService.closeLot(id, userId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateLotResponseDTO> updateLot(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id,
            @RequestBody UpdateLotRequestDTO dto
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        Lot updated = lotService.updateLot(userId, id, dto);

        return ResponseEntity.ok(lotMapper.toDTO(updated));
    }
}