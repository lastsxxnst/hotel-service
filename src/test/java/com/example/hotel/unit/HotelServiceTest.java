package com.example.hotel.unit;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.service.HotelService;
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
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void shouldCreateHotel() {

        Hotel hotel = new Hotel();
        hotel.setName("Grand Hotel");

        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel result = hotelService.createHotel(hotel);

        assertNotNull(result);
        assertEquals("Grand Hotel", result.getName());

        verify(hotelRepository).save(hotel);
    }

    @Test
    void shouldReturnAllHotels() {

        when(hotelRepository.findAll()).thenReturn(List.of(
                new Hotel(),
                new Hotel()
        ));

        List<Hotel> result = hotelService.getAllHotels();

        assertEquals(2, result.size());

        verify(hotelRepository).findAll();
    }

    @Test
    void shouldReturnHotelById() {

        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Grand Hotel");

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        Hotel result = hotelService.getHotelById(1L);

        assertNotNull(result);
        assertEquals("Grand Hotel", result.getName());
    }

    @Test
    void shouldDeleteHotel() {

        doNothing().when(hotelRepository).deleteById(1L);

        hotelService.deleteHotel(1L);

        verify(hotelRepository).deleteById(1L);
    }
}