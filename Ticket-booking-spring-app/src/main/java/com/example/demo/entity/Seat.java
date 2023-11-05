package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="room_number", nullable = false)
    private int roomNumber;
    @Column(name="row", nullable = false)
    private char row;
    @Column(name="seat_number", nullable = false)
    private int seatNumber;

    public Seat() {}

    public Seat(int roomNumber, char row, int seatNumber) {
        this.roomNumber = roomNumber;
        this.row = row;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", row='" + row + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
