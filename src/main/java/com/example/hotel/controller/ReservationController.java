package com.example.hotel.controller;

import com.example.hotel.dto.ReservationRequest;
import com.example.hotel.model.Reservation;
import com.example.hotel.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    /**
     * Creates a new reservation.
     *
     * @param request reservation request DTO
     * @return created reservation
     */

    @PostMapping
    public Reservation create(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(
                request.getRoomId(),
                request.getGuestId(),
                LocalDate.parse(request.getCheckIn()),
                LocalDate.parse(request.getCheckOut())
        );
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/{id}")
    public void cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
    }
}