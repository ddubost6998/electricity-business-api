package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO getReservationById(Long id);

    List<ReservationDTO> getAllReservations();

    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

    void deleteReservation(Long id);

    List<ReservationDTO> getReservationsByUserId(Long userId);

    List<ReservationDTO> getReservationsByChargingStationId(Long chargingStationId);
}
