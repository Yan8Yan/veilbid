package com.project.veilbid.services.impl;

import com.project.veilbid.domain.dto.UserProfileDTO;
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
public class UserService {

    private final UserRepository userRepository;

    public UserProfileDTO getCurrentUser(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserProfileDTO.builder()
                .name(user.getName())
                .info(user.getInfo())
                .email(user.getEmail())
                .build();
    }

    public UserProfileDTO updateCurrentUser(
            UUID userId,
            UserProfileDTO dto
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setInfo(dto.getInfo());

        userRepository.save(user);

        return UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .info(user.getInfo())
                .email(user.getEmail())
                .build();
    }
}