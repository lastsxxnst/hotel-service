package com.example.hotel.unit;

import com.example.hotel.model.Room;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void shouldCreateRoom() {

        Room room = new Room();
        room.setRoomNumber("101");
        room.setPrice(50.0);
        room.setType("DOUBLE");

        Room saved = new Room();
        saved.setId(1L);
        saved.setRoomNumber("101");

        when(roomRepository.save(room)).thenReturn(saved);

        Room result = roomService.createRoom(room);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("101", result.getRoomNumber());

        verify(roomRepository).save(room);
    }

    @Test
    void shouldReturnRoomById() {

        Room room = new Room();
        room.setId(1L);
        room.setRoomNumber("101");

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Room result = roomService.getRoomById(1L);

        assertNotNull(result);
        assertEquals("101", result.getRoomNumber());

        verify(roomRepository).findById(1L);
    }

    @Test
    void shouldReturnAllRooms() {

        when(roomRepository.findAll()).thenReturn(List.of(new Room(), new Room()));

        List<Room> result = roomService.getAllRooms();

        assertEquals(2, result.size());
    }

    @Test
    void shouldDeleteRoom() {

        doNothing().when(roomRepository).deleteById(1L);

        roomService.deleteRoom(1L);

        verify(roomRepository).deleteById(1L);
    }
}