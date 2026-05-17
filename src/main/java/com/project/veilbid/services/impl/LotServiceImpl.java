package com.project.veilbid.services.impl;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.entities.User;
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
                .orElseThrow(() -> new UserNotFoundException(String.format("User with ID '%s' not found", sellerId)));
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
        return lotRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lot not found: " + id));
    }

    @Override
    public List<Lot> getAllLots(String lotType) {

        if (lotType == null || lotType.isEmpty()) {
            return lotRepository.findAll();
        }

        return lotRepository.findByLotType(lotType);
    }
}
