package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.dto.ReservationRequestDTO; // Assurez-vous d'utiliser RequestDTO

import java.util.List;

public interface ReservationService {

    ReservationDTO createReservation(ReservationRequestDTO reservationRequestDTO);

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO getReservationById(Long id);

    List<ReservationDTO> getAllReservations();

    ReservationDTO updateReservation(Long id, ReservationRequestDTO reservationRequestDTO);

    void deleteReservation(Long id);

    ReservationDTO acceptReservation(Long id);

    ReservationDTO rejectReservation(Long id);

    List<ReservationDTO> getCurrentReservations(Long userId);

    List<ReservationDTO> getPastReservations(Long userId);
}
