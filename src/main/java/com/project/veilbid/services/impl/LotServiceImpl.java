package com.project.veilbid.services.impl;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.entities.User;
import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;
import com.project.veilbid.domain.requests.CreateLotRequest;
import com.project.veilbid.exceptions.UserNotFoundException;
import com.project.veilbid.repositories.LotRepository;
import com.project.veilbid.repositories.UserRepository;
import com.project.veilbid.services.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final UserRepository userRepository;
    private final LotRepository lotRepository;

    @Override
    public Lot createLotRequest(UUID sellerId, CreateLotRequest lot) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID '%s' not found", sellerId)
                ));

        Lot lotToCreate = new Lot();
        lotToCreate.setTitle(lot.getTitle());
        lotToCreate.setDescription(lot.getDescription());
        lotToCreate.setStartTime(lot.getStartTime());
        lotToCreate.setEndTime(lot.getEndTime());
        lotToCreate.setStatus(lot.getStatus());
        lotToCreate.setLotType(lot.getLotType());
        lotToCreate.setImageUrl(lot.getImageUrl());
        lotToCreate.setStartingPrice(lot.getStartingPrice());
        lotToCreate.setCurrentPrice(lot.getStartingPrice());
        lotToCreate.setLocation(lot.getLocation());
        lotToCreate.setSeller(seller);

        return lotRepository.save(lotToCreate);
    }

    @Override
    public Lot findById(UUID id) {
        return lotRepository.findByIdWithSeller(id)
                .orElseThrow(() -> new RuntimeException("Lot not found: " + id));
    }

    // =========================
    // 🔥 SAFE PARSER (ВАЖНО)
    // =========================
    private LotType parseLotType(String lotType) {
        if (lotType == null || lotType.isBlank()) return null;

        try {
            return LotType.valueOf(lotType.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid lotType: " + lotType);
        }
    }

    @Override
    public List<Lot> getAllLots(String lotType, String search) {

        LotType typeEnum = parseLotType(lotType);

        boolean hasType = typeEnum != null;
        boolean hasSearch = search != null && !search.isBlank();

        // 1. без фильтров
        if (!hasType && !hasSearch) {
            return lotRepository.findAll();
        }

        // 2. type + search
        if (hasType && hasSearch) {
            return lotRepository.findByLotTypeAndTitleContainingIgnoreCase(
                    typeEnum,
                    search
            );
        }

        // 3. только type
        if (hasType) {
            return lotRepository.findByLotType(typeEnum);
        }

        // 4. только search
        return lotRepository.findByTitleContainingIgnoreCase(search);
    }

    @Override
    public List<Lot> getMyLots(UUID sellerId) {
        return lotRepository.findBySellerId(sellerId);
    }

    @Override
    public void deleteLot(UUID userId, UUID lotId) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> new RuntimeException("Lot not found"));

        if (!lot.getSeller().getId().equals(userId)) {
            throw new RuntimeException("You can't delete this lot");
        }

        lotRepository.delete(lot);
    }

    @Override
    public void closeLot(UUID lotId, UUID userId) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> new RuntimeException("Lot not found"));

        if (!lot.getSeller().getId().equals(userId)) {
            throw new RuntimeException("Not your lot");
        }

        lot.setStatus(LotStatus.CLOSED);
        lotRepository.save(lot);
    }
}