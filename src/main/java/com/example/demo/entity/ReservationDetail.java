package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="reservation_id", nullable = false)
    private int reservationId;
    @Column(name="seat_id", nullable = false)
    private int seatId;

    public ReservationDetail() {}

    public ReservationDetail(int reservationId, int seatId) {
        this.reservationId = reservationId;
        this.seatId = seatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "ReservationDetail{" +
                "id=" + id +
                ", reservationId=" + reservationId +
                ", seatId=" + seatId +
                '}';
    }
}
