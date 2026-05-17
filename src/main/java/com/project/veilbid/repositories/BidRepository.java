package com.project.veilbid.repositories;

import com.project.veilbid.domain.entities.Bid;
import com.project.veilbid.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BidRepository extends JpaRepository<Bid, UUID> {
    List<Bid> findByLotIdOrderByAmountDesc(UUID lotId);
}
