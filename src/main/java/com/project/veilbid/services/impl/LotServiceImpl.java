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
        lotToCreate.setStart(lot.getStart());
        lotToCreate.setEnd(lot.getEnd());
        lotToCreate.setStatus(lot.getStatus());
        lotToCreate.setType(lot.getLotType());
        lotToCreate.setImageUrl(lot.getImageUrl());
        lotToCreate.setStartingPrice(lot.getStartingPrice());
        lotToCreate.setCurrentPrice(lot.getCurrentPrice());
        lotToCreate.setLocation(lot.getLocation());
        lotToCreate.setSeller(seller);

        lotRepository.save(lotToCreate);

        return null;
    }
}
