package com.project.veilbid.repositories;

import com.project.veilbid.domain.entities.Lot;
import com.project.veilbid.domain.enums.LotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LotRepository extends JpaRepository<Lot, UUID> {
    List<Lot> findByLotType(String lotType);
    @Query("SELECT l FROM Lot l JOIN FETCH l.seller WHERE l.id = :id")
    Optional<Lot> findByIdWithSeller(UUID id);
    List<Lot> findBySellerId(UUID sellerId);
    List<Lot> findAllByStatusAndEndTimeBefore(LotStatus status, LocalDateTime endTime);
}
