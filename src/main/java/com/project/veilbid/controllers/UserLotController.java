package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.mappers.LotMapper;
import com.project.veilbid.services.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/lots")
@RequiredArgsConstructor
public class UserLotController {

    private final LotService lotService;
    private final LotMapper lotMapper;

    // МОИ ЛОТЫ
    @GetMapping("/me")
    public ResponseEntity<List<CreateLotResponseDTO>> getMyLots(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        List<CreateLotResponseDTO> result = lotService.getLotsBySeller(userId)
                .stream()
                .map(lotMapper::toDTO)
                .toList();

        return ResponseEntity.ok(result);
    }
}