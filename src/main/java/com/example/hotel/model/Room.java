package com.example.hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private Double price;

    private String type;

    private Long hotelId;

    // GETTERS / SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
}