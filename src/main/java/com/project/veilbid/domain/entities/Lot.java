package com.project.veilbid.domain.entities;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "started", nullable = false, updatable = false)
    private LocalDateTime started;

    @Column(name = "ended")
    private LocalDateTime ended;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LotStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LotType type;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "starting_price", nullable = false)
    private BigDecimal startingPrice;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    private List<Bid> bids = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(id, lot.id) && Objects.equals(title, lot.title) && Objects.equals(description, lot.description) && Objects.equals(started, lot.started) && Objects.equals(ended, lot.ended) && status == lot.status && type == lot.type && Objects.equals(imageUrl, lot.imageUrl) && Objects.equals(startingPrice, lot.startingPrice) && Objects.equals(currentPrice, lot.currentPrice) && Objects.equals(location, lot.location) && Objects.equals(createdAt, lot.createdAt) && Objects.equals(updated, lot.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, started, ended, status, type, imageUrl, startingPrice, currentPrice, location, createdAt, updated);
    }
}