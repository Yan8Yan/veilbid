package com.project.veilbid.services.impl;

import com.project.veilbid.domain.entities.Bid;
import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.entities.User;
import com.project.veilbid.domain.requests.CreateBidRequest;
import com.project.veilbid.repositories.BidRepository;
import com.project.veilbid.repositories.LotRepository;
import com.project.veilbid.repositories.UserRepository;
import com.project.veilbid.services.BidService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final LotRepository lotRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Bid createBid(UUID userId, CreateBidRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Lot lot = lotRepository.findById(request.getLotId())
                .orElseThrow(() -> new RuntimeException("Lot not found"));

        if (lot.getCurrentPrice().compareTo(request.getAmount()) >= 0) {
            throw new RuntimeException("Bid must be higher than current price");
        }

        Bid bid = new Bid();
        bid.setAmount(request.getAmount());
        bid.setLot(lot);
        bid.setBider(user);
        bid.setCreatedAt(LocalDateTime.now());

        lot.setCurrentPrice(request.getAmount());
        lotRepository.save(lot);

        return bidRepository.save(bid);
    }
}