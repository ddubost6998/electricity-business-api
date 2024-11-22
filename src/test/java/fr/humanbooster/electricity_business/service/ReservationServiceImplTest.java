package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.mapper.ReservationMapper;
import fr.humanbooster.electricity_business.model.Reservation;
import fr.humanbooster.electricity_business.repository.ReservationRepository;
import fr.humanbooster.electricity_business.service.impl.ReservationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setUserId(1L);
        reservationDTO.setChargingStationId(5L);
        reservationDTO.setStartTime(LocalDateTime.of(2024, 11, 25, 10, 0));
        reservationDTO.setEndTime(LocalDateTime.of(2024, 11, 25, 12, 0));
        reservationDTO.setTotalPrice(20.0);
        reservationDTO.setStatus(ReservationDTO.ReservationStatus.PENDING);

        Reservation reservation = new Reservation();
        when(reservationMapper.toEntity(reservationDTO)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDTO(reservation)).thenReturn(reservationDTO);

        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);

        assertNotNull(createdReservation);
        assertEquals(1L, createdReservation.getUserId());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testGetReservationById() {

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1L);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationMapper.toDTO(reservation)).thenReturn(reservationDTO);

        ReservationDTO result = reservationService.getReservationById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetReservationById_NotFound() {

        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservationService.getReservationById(1L));
        assertEquals("Reservation not found with id: 1", exception.getMessage());
    }
}
