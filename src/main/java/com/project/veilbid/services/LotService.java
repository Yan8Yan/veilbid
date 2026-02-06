package com.project.veilbid.services;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.requests.CreateLotRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface LotService {
    Lot createLotRequest(UUID sellerId, CreateLotRequest lot);
}
