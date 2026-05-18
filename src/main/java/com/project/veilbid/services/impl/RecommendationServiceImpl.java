package com.project.veilbid.services.impl;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.repositories.LotRepository;
import com.project.veilbid.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl
        implements RecommendationService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final LotRepository lotRepository;

    @Override
    public void trackGlobalLot(UUID lotId) {

        redisTemplate.opsForZSet()
                .incrementScore(
                        "global:popular",
                        lotId.toString(),
                        1
                );
    }

    @Override
    public List<Lot> getTrendingLots() {

        Set<Object> ids = redisTemplate.opsForZSet()
                .reverseRange("global:popular", 0, 9);

        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<UUID> lotIds = ids.stream()
                .map(id -> UUID.fromString(id.toString().replace("\"", "")))
                .toList();

        return lotRepository.findAllById(lotIds);
    }


}