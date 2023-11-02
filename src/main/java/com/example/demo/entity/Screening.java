package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="movie_title", nullable = false)
    private String movieTitle;
    @Column(name="room_number", nullable = false)
    private int roomNumber;
    @Column(name="date", nullable = false)
    private LocalDate date;
    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    public Screening() {}

    public Screening(String movieTitle, int roomNumber, LocalDate date, LocalTime startTime) {
        this.movieTitle = movieTitle;
        this.roomNumber = roomNumber;
        this.date = date;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String moveTitle) {
        this.movieTitle = moveTitle;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movieTitle='" + movieTitle + '\'' +
                ", roomNumber=" + roomNumber +
                ", date=" + date +
                ", startTime=" + startTime +
                '}';
    }
}
