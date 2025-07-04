package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.dto.ReservationRequestDTO;
import fr.humanbooster.electricity_business.mapper.ReservationMapper;
import fr.humanbooster.electricity_business.model.ChargingStation;
import fr.humanbooster.electricity_business.model.Reservation;
import fr.humanbooster.electricity_business.model.ReservationStatus;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.repository.ChargingStationRepository;
import fr.humanbooster.electricity_business.repository.ReservationRepository;
import fr.humanbooster.electricity_business.repository.UserRepository;
import fr.humanbooster.electricity_business.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final UserRepository userRepository;
    private final ChargingStationRepository chargingStationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper,
                                  UserRepository userRepository,
                                  ChargingStationRepository chargingStationRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.userRepository = userRepository;
        this.chargingStationRepository = chargingStationRepository;
    }

    @Transactional
    @Override
    public ReservationDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        if (reservationRequestDTO.getStartTime().isAfter(reservationRequestDTO.getEndTime())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }

        User user = userRepository.findById(reservationRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + reservationRequestDTO.getUserId()));

        ChargingStation chargingStation = chargingStationRepository.findById(reservationRequestDTO.getChargingStationId())
                .orElseThrow(() -> new RuntimeException("Borne de recharge non trouvée avec l'ID : " + reservationRequestDTO.getChargingStationId()));

        if (!chargingStation.getIsAvailable()) {
            throw new RuntimeException("La borne de recharge n'est pas disponible pour de nouvelles réservations.");
        }

        Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);
        reservation.setUser(user);
        reservation.setChargingStation(chargingStation);
        reservation.setStatus(ReservationStatus.PENDING);

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        return null;
    }

    @Transactional
    @Override
    public ReservationDTO updateReservation(Long id, ReservationRequestDTO reservationRequestDTO) { // Prend un RequestDTO
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));

        reservationMapper.updateEntityFromRequestDto(reservationRequestDTO, existingReservation);

        if (reservationRequestDTO.getUserId() != null && !existingReservation.getUser().getId().equals(reservationRequestDTO.getUserId())) {
            User newUser = userRepository.findById(reservationRequestDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("Nouvel utilisateur non trouvé avec l'ID : " + reservationRequestDTO.getUserId()));
            existingReservation.setUser(newUser);
        }
        if (reservationRequestDTO.getChargingStationId() != null && !existingReservation.getChargingStation().getId().equals(reservationRequestDTO.getChargingStationId())) {
            ChargingStation newStation = chargingStationRepository.findById(reservationRequestDTO.getChargingStationId())
                    .orElseThrow(() -> new RuntimeException("Nouvelle borne non trouvée avec l'ID : " + reservationRequestDTO.getChargingStationId()));
            existingReservation.setChargingStation(newStation);
        }

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.toDto(updatedReservation);
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Réservation non trouvée avec l'ID : " + id);
        }
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDTO> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId).stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByChargingStationId(Long chargingStationId) {
        return reservationRepository.findByChargingStationId(chargingStationId).stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservationDTO acceptReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("La réservation n'est pas en attente, impossible de l'accepter.");
        }

        reservation.setStatus(ReservationStatus.ACCEPTED);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(updatedReservation);
    }

    @Transactional
    public ReservationDTO rejectReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID : " + id));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("La réservation n'est pas en attente, impossible de la refuser.");
        }

        reservation.setStatus(ReservationStatus.REJECTED);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(updatedReservation);
    }
}