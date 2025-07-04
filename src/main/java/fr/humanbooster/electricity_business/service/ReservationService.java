package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.dto.ReservationRequestDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationService {

    @Transactional
    ReservationDTO createReservation(ReservationRequestDTO reservationRequestDTO);

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO getReservationById(Long id);

    List<ReservationDTO> getAllReservations();

    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

    @Transactional
    ReservationDTO updateReservation(Long id, ReservationRequestDTO reservationRequestDTO);

    void deleteReservation(Long id);

    List<ReservationDTO> getReservationsByUserId(Long userId);

    List<ReservationDTO> getReservationsByChargingStationId(Long chargingStationId);
}
