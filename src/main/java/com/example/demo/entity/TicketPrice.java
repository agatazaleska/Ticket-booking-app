package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TicketPrice {
    @Id
    @Column(name="type", nullable = false)
    private String type;
    @Column(name="price", nullable = false)
    private float price;

    public TicketPrice(String type, float price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketPrice{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
