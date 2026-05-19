package com.project.veilbid.repositories;

import com.project.veilbid.domain.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {

    Optional<Favorite> findByUserIdAndLotId(UUID userId, UUID lotId);

    List<Favorite> findByUserId(UUID userId);
}