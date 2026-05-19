package com.project.veilbid.services.impl;

import com.project.veilbid.domain.entities.Favorite;
import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.entities.User;
import com.project.veilbid.repositories.FavoriteRepository;
import com.project.veilbid.repositories.LotRepository;
import com.project.veilbid.repositories.UserRepository;
import com.project.veilbid.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final LotRepository lotRepository;

    @Override
    public void addFavorite(UUID userId, UUID lotId) {

        if (favoriteRepository.findByUserIdAndLotId(userId, lotId).isPresent()) {
            return;
        }

        User user = userRepository.findById(userId)
                .orElseThrow();

        Lot lot = lotRepository.findById(lotId)
                .orElseThrow();

        Favorite fav = new Favorite();
        fav.setUser(user);
        fav.setLot(lot);
        fav.setCreatedAt(LocalDateTime.now());

        favoriteRepository.save(fav);
    }

    @Override
    public void removeFavorite(UUID userId, UUID lotId) {

        favoriteRepository.findByUserIdAndLotId(userId, lotId)
                .ifPresent(favoriteRepository::delete);
    }

    @Override
    public List<Lot> getUserFavorites(UUID userId) {
        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(Favorite::getLot)
                .toList();
    }
}