package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.dto.ChargingStationRequestDTO;
import fr.humanbooster.electricity_business.mapper.ChargingStationMapper;
import fr.humanbooster.electricity_business.model.ChargingStation;
import fr.humanbooster.electricity_business.model.Location;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.repository.ChargingStationRepository;
import fr.humanbooster.electricity_business.repository.LocationRepository;
import fr.humanbooster.electricity_business.repository.ReservationRepository;
import fr.humanbooster.electricity_business.repository.UserRepository;
import fr.humanbooster.electricity_business.service.ChargingStationService;
import fr.humanbooster.electricity_business.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final ChargingStationMapper chargingStationMapper;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ReservationRepository reservationRepository;

    public ChargingStationServiceImpl(ChargingStationRepository chargingStationRepository,
                                      ChargingStationMapper chargingStationMapper,
                                      UserRepository userRepository,
                                      LocationRepository locationRepository, ReservationRepository reservationRepository) {
        this.chargingStationRepository = chargingStationRepository;
        this.chargingStationMapper = chargingStationMapper;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @Override
    public ChargingStationDTO createChargingStation(ChargingStationRequestDTO chargingStationRequestDTO) {
        ChargingStation chargingStation = chargingStationMapper.toEntity(chargingStationRequestDTO);

        User owner = userRepository.findById(chargingStationRequestDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé avec l'ID : " + chargingStationRequestDTO.getOwnerId()));
        chargingStation.setOwner(owner);

        Location location = locationRepository.findById(chargingStationRequestDTO.getLocationId())
                .orElseThrow(() -> new RuntimeException("Localisation non trouvée avec l'ID : " + chargingStationRequestDTO.getLocationId()));
        chargingStation.setLocation(location);

        ChargingStation savedStation = chargingStationRepository.save(chargingStation);
        return chargingStationMapper.toDto(savedStation);
    }

    @Override
    public ChargingStationDTO createChargingStation(ChargingStationDTO chargingStationDTO) {
        return null;
    }

    @Override
    public ChargingStationDTO getChargingStationById(Long id) {
        return chargingStationRepository.findById(id)
                .map(chargingStationMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Borne de recharge non trouvée avec l'ID : " + id));
    }

    @Override
    public List<ChargingStationDTO> getAllChargingStations() {
        return chargingStationRepository.findAll().stream()
                .map(chargingStationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChargingStationDTO updateChargingStation(Long id, ChargingStationDTO chargingStationDTO) {
        return null;
    }

    @Transactional
    @Override
    public ChargingStationDTO updateChargingStation(Long id, ChargingStationRequestDTO chargingStationRequestDTO) { // Prend un RequestDTO
        ChargingStation existingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borne de recharge non trouvée avec l'ID : " + id));

        chargingStationMapper.updateEntityFromRequestDto(chargingStationRequestDTO, existingStation);

        if (chargingStationRequestDTO.getOwnerId() != null &&
                !existingStation.getOwner().getId().equals(chargingStationRequestDTO.getOwnerId())) {
            User newOwner = userRepository.findById(chargingStationRequestDTO.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Nouvel propriétaire non trouvé avec l'ID : " + chargingStationRequestDTO.getOwnerId()));
            existingStation.setOwner(newOwner);
        }

        if (chargingStationRequestDTO.getLocationId() != null &&
                !existingStation.getLocation().getId().equals(chargingStationRequestDTO.getLocationId())) {
            Location newLocation = locationRepository.findById(chargingStationRequestDTO.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Nouvelle localisation non trouvée avec l'ID : " + chargingStationRequestDTO.getLocationId()));
            existingStation.setLocation(newLocation);
        }

        ChargingStation updatedStation = chargingStationRepository.save(existingStation);
        return chargingStationMapper.toDto(updatedStation);
    }

    @Override
    @Transactional
    public void deleteChargingStation(Long id) {
        boolean hasReservations = reservationRepository.existsByChargingStationId(id);
        if (hasReservations) {
            throw new RuntimeException("Impossible de supprimer la borne de recharge car elle a des réservations.");
        }

        if (!chargingStationRepository.existsById(id)) {
            throw new RuntimeException("Borne de recharge non trouvée avec l'ID : " + id);
        }
        chargingStationRepository.deleteById(id);
    }

    @Override
    public List<ChargingStationDTO> getChargingStationsByAvailability(boolean isAvailable) {
        return chargingStationRepository.findByIsAvailable(isAvailable).stream()
                .map(chargingStationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChargingStationDTO> getChargingStationsByLocation(Long locationId) {
        return chargingStationRepository.findByLocationId(locationId).stream()
                .map(chargingStationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ChargingStationDTO setHourlyRate(Long id, Double newHourlyRate) {
        if (newHourlyRate == null || newHourlyRate < 0) {
            throw new IllegalArgumentException("Le tarif horaire doit être positif.");
        }
        ChargingStation existingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borne de recharge non trouvée avec l'ID : " + id));
        existingStation.setHourlyRate(newHourlyRate);
        ChargingStation updatedStation = chargingStationRepository.save(existingStation);
        return chargingStationMapper.toDto(updatedStation);
    }
}