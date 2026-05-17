package com.project.veilbid.services;

import com.project.veilbid.domain.entities.Bid;
import com.project.veilbid.domain.requests.CreateBidRequest;

import java.util.List;
import java.util.UUID;

public interface BidService {
    Bid createBid(UUID userId, CreateBidRequest request);
}