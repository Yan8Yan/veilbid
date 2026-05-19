package com.project.veilbid.services;

import com.project.veilbid.domain.entities.Lot;

import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    void addFavorite(UUID userId, UUID lotId);
    void removeFavorite(UUID userId, UUID lotId);
    List<Lot> getUserFavorites(UUID userId);
}