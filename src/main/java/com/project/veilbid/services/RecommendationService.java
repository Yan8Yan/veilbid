package com.project.veilbid.services;

import com.project.veilbid.domain.entities.Lot;

import java.util.List;
import java.util.UUID;

public interface RecommendationService {

    void trackGlobalLot(UUID lotId);
    List<Lot> getTrendingLots();

}