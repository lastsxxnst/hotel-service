package com.example.hotel.service;

import com.example.hotel.model.Guest;
import com.example.hotel.model.Reservation;
import com.example.hotel.model.Room;
import com.example.hotel.repository.GuestRepository;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              RoomRepository roomRepository,
                              GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    public Reservation createReservation(Long roomId,
                                         Long guestId,
                                         LocalDate checkIn,
                                         LocalDate checkOut) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Room not found"));

        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Guest not found"));

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setGuest(guest);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}