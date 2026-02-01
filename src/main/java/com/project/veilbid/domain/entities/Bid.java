package com.project.veilbid.domain.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    public Bid() {
    }

    public Bid(UUID id, LocalDateTime created, Double amount, Lot lot) {
        this.id = id;
        this.created = created;
        this.amount = amount;
        this.lot = lot;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return Objects.equals(id, bid.id) && Objects.equals(created, bid.created) && Objects.equals(amount, bid.amount) && Objects.equals(lot, bid.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, amount, lot);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", created=" + created +
                ", amount=" + amount +
                ", lot=" + lot +
                '}';
    }
}
