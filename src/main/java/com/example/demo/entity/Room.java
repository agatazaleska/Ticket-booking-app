package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @Column(name="number")
    private int number;

    @Column(name="total_rows")
    private int totalRows;

    @Column(name="total_seats_in_one_row")
    private int totalSeatsInOneRow;

    public Room(int number, int totalRows, int totalSeatsInOneRow) {
        this.number = number;
        this.totalRows = totalRows;
        this.totalSeatsInOneRow = totalSeatsInOneRow;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalSeatsInOneRow() {
        return totalSeatsInOneRow;
    }

    public void setTotalSeatsInOneRow(int totalSeatsInOneRow) {
        this.totalSeatsInOneRow = totalSeatsInOneRow;
    }
}
