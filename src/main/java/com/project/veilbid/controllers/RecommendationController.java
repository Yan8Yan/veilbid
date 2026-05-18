package com.project.veilbid.controllers;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.dto.CreateLotResponseDTO;
import com.project.veilbid.mappers.LotMapper;
import com.project.veilbid.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final LotMapper lotMapper;

    @GetMapping("/trending")
    public ResponseEntity<List<CreateLotResponseDTO>> getTrendingLots() {

        List<CreateLotResponseDTO> result = recommendationService.getTrendingLots()
                .stream()
                .map(lotMapper::toDTO)
                .toList();

        return ResponseEntity.ok(result);
    }
}
