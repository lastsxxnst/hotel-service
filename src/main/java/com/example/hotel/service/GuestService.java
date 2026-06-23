package com.example.hotel.service;

import com.example.hotel.model.Guest;
import com.example.hotel.repository.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Guest not found"
                ));
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}