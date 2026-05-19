package com.project.veilbid.scheduler;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.repositories.LotRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LotScheduler {

    private final LotRepository lotRepository;

    public LotScheduler(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void closeExpiredLots() {

        LocalDateTime now = LocalDateTime.now();

        var expiredLots = lotRepository.findAllByStatusAndEndTimeBefore(
                LotStatus.OPEN,
                now
        );

        for (var lot : expiredLots) {
            lot.setStatus(LotStatus.CLOSED);
        }

        lotRepository.saveAll(expiredLots);
    }
}