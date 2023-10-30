package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

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
    @Column(name="screening_date", nullable = false)
    private Date screeningDate;
    @Column(name="screening_time", nullable = false)
    private Time screeningTime;

    public Screening() {}

    public Screening(String movieTitle, int roomNumber, Date screeningDate, Time screeningTime) {
        this.movieTitle = movieTitle;
        this.roomNumber = roomNumber;
        this.screeningDate = screeningDate;
        this.screeningTime = screeningTime;
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

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public Time getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(Time screeningTime) {
        this.screeningTime = screeningTime;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movieTitle='" + movieTitle + '\'' +
                ", roomNumber=" + roomNumber +
                ", screeningDate=" + screeningDate +
                ", screeningTime=" + screeningTime +
                '}';
    }
}
