package com.example.hotel.controller;

import com.example.hotel.dto.RoomRequest;
import com.example.hotel.model.Room;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room create(@RequestBody RoomRequest request) {

        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setType(request.getType());
        room.setHotelId(request.getHotelId());

        return roomService.createRoom(room);
    }

    @GetMapping
    public List<Room> getAll() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}