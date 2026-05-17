package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.CreateBidRequestDTO;
import com.project.veilbid.domain.dto.CreateBidResponseDTO;
import com.project.veilbid.domain.entities.Bid;
import com.project.veilbid.domain.requests.CreateBidRequest;
import com.project.veilbid.mappers.BidMapper;
import com.project.veilbid.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lots/{id}/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;
    private final BidMapper bidMapper;

    @PostMapping
    public ResponseEntity<CreateBidResponseDTO> createBid(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id,
            @RequestBody CreateBidRequestDTO dto
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        CreateBidRequest request = new CreateBidRequest();

        request.setAmount(dto.getAmount());
        request.setLotId(id);

        Bid bid = bidService.createBid(userId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bidMapper.toDTO(bid));
    }
}