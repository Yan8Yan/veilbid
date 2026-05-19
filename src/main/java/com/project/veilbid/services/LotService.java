package com.project.veilbid.services;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.requests.CreateLotRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LotService {
    Lot createLotRequest(UUID sellerId, CreateLotRequest lot);
    Lot findById(UUID id);
    List<Lot> getAllLots(String lotType);
    List<Lot> getMyLots(UUID sellerId);
    void deleteLot(UUID userId, UUID lotId);
    void closeLot(UUID lotId, UUID userId);
}
