package com.project.veilbid.domain.entities;

import com.project.veilbid.domain.enums.LotStatus;
import com.project.veilbid.domain.enums.LotType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LotStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LotType type;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "starting_price", nullable = false)
    private Double startingPrice;

    @Column(name = "current_price")
    private Double currentPrice;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "location")
    private String location;

    @Column(name = "views_count")
    private Integer viewsCount = 0;

    @OneToMany(mappedBy = "lot", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Bid> bids;

    public Lot() {
    }

    public Lot(UUID id, String title, String description, LocalDateTime dueDate, LocalDateTime created, LocalDateTime updated, LotStatus status, LotType type, String imageUrl, Double startingPrice, Double currentPrice, UUID sellerId, String location, Integer viewsCount, List<Bid> bids) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.type = type;
        this.imageUrl = imageUrl;
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;
        this.sellerId = sellerId;
        this.location = location;
        this.viewsCount = viewsCount;
        this.bids = bids;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LotStatus getStatus() {
        return status;
    }

    public void setStatus(LotStatus status) {
        this.status = status;
    }

    public LotType getType() {
        return type;
    }

    public void setType(LotType type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(id, lot.id) && Objects.equals(title, lot.title) && Objects.equals(description, lot.description) && Objects.equals(dueDate, lot.dueDate) && Objects.equals(created, lot.created) && Objects.equals(updated, lot.updated) && status == lot.status && type == lot.type && Objects.equals(imageUrl, lot.imageUrl) && Objects.equals(startingPrice, lot.startingPrice) && Objects.equals(currentPrice, lot.currentPrice) && Objects.equals(sellerId, lot.sellerId) && Objects.equals(location, lot.location) && Objects.equals(viewsCount, lot.viewsCount) && Objects.equals(bids, lot.bids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, created, updated, status, type, imageUrl, startingPrice, currentPrice, sellerId, location, viewsCount, bids);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", created=" + created +
                ", updated=" + updated +
                ", status=" + status +
                ", type=" + type +
                ", imageUrl='" + imageUrl + '\'' +
                ", startingPrice=" + startingPrice +
                ", currentPrice=" + currentPrice +
                ", sellerId=" + sellerId +
                ", location='" + location + '\'' +
                ", viewsCount=" + viewsCount +
                ", bids=" + bids +
                '}';
    }
}