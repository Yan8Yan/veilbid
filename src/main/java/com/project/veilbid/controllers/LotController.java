package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.CreateLotRequestDTO;
import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.requests.CreateLotRequest;
import com.project.veilbid.mappers.LotMapper;
import com.project.veilbid.services.LotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lots")
@RequiredArgsConstructor
public class LotController {

    private final LotMapper lotMapper;
    private final LotService lotService;

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
}
