package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SeatAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="screening_id")
    private int screeningId;
    @Column(name="seat_id")
    private int seatId;
    @Column(name="available")
    private Boolean available;

    SeatAvailability() {}

    public SeatAvailability(int screeningId, int seatId, Boolean available) {
        this.screeningId = screeningId;
        this.seatId = seatId;
        this.available = available;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SeatAvailability{" +
                "id=" + id +
                ", screeningId=" + screeningId +
                ", seatId=" + seatId +
                ", available=" + available +
                '}';
    }
}
