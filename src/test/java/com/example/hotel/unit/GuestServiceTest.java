package com.example.hotel.unit;

import com.example.hotel.model.Guest;
import com.example.hotel.repository.GuestRepository;
import com.example.hotel.service.GuestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    @Test
    void shouldCreateGuest() {

        Guest guest = new Guest();
        guest.setName("John");

        when(guestRepository.save(guest)).thenReturn(guest);

        Guest result = guestService.createGuest(guest);

        assertNotNull(result);
        assertEquals("John", result.getName());

        verify(guestRepository).save(guest);
    }

    @Test
    void shouldReturnAllGuests() {

        List<Guest> list = List.of(
                new Guest(),
                new Guest()
        );

        when(guestRepository.findAll()).thenReturn(list);

        List<Guest> result = guestService.getAllGuests();

        assertEquals(2, result.size());
        verify(guestRepository).findAll();
    }

    @Test
    void shouldReturnGuestById() {

        Guest guest = new Guest();
        guest.setId(1L);
        guest.setName("John");

        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

        Guest result = guestService.getGuestById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenGuestNotFound() {

        when(guestRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            guestService.getGuestById(99L);
        });
    }

    @Test
    void shouldDeleteGuest() {

        Long id = 1L;

        doNothing().when(guestRepository).deleteById(id);

        guestService.deleteGuest(id);

        verify(guestRepository).deleteById(id);
    }
}