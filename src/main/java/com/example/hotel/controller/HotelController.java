package com.example.hotel.controller;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for room management.
 *
 * <p>
 * Handles CRUD operations for rooms.
 * </p>
 */

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getAll() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}