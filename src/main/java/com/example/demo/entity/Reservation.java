package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="screening_id", nullable = false)
    private int screeningId;
    @Column(name="customer_name", nullable = false)
    private String customerName;
    @Column(name="customer_surname", nullable = false)
    private String customerSurname;
    @Column(name="total_cost", nullable = false)
    private float totalCost;
    @Column(name="expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    public Reservation() {}

    public Reservation(String customerName, String customerSurname, float totalCost, LocalDateTime expirationDate) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.totalCost = totalCost;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", totalCost=" + totalCost +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
