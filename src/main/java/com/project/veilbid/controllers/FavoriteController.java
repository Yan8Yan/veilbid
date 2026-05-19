package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.mappers.LotMapper;
import com.project.veilbid.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final LotMapper lotMapper;

    @PostMapping("/{lotId}")
    public ResponseEntity<Void> addFavorite(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID lotId
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        favoriteService.addFavorite(userId, lotId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{lotId}")
    public ResponseEntity<Void> removeFavorite(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID lotId
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        favoriteService.removeFavorite(userId, lotId);
        return ResponseEntity.ok().build();
    }

}