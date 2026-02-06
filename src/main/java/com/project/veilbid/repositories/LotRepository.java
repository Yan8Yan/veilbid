package com.project.veilbid.repositories;

import com.project.veilbid.domain.entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LotRepository extends JpaRepository<Lot, UUID> {
}
