package com.example.hotel.controller;

import com.example.hotel.model.Guest;
import com.example.hotel.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing guests.
 *
 * <p>
 * Provides CRUD operations for Guest entity.
 * </p>
 */

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    /**
     * Constructor injection of GuestService.
     *
     * @param guestService service layer for guests
     */

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    /**
     * Creates a new guest.
     *
     * @param guest guest data
     * @return created guest
     */

    @PostMapping
    public Guest create(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }
    /**
     * Returns all hotels.
     *
     * @return list of hotels
     */

    @GetMapping
    public List<Guest> getAll() {
        return guestService.getAllGuests();
    }
    /**
     * Returns hotel by ID.
     *
     * @param id hotel id
     * @return hotel entity
     */

    @GetMapping("/{id}")
    public Guest getById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }
    /**
     * Deletes hotel by ID.
     *
     * @param id hotel id
     */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }
}