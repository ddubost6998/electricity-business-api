package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.dto.ChargingStationRequestDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChargingStationService {

    @Transactional
    ChargingStationDTO createChargingStation(ChargingStationRequestDTO chargingStationRequestDTO);

    ChargingStationDTO createChargingStation(ChargingStationDTO chargingStationDTO);

    ChargingStationDTO getChargingStationById(Long id);

    List<ChargingStationDTO> getAllChargingStations();

    ChargingStationDTO updateChargingStation(Long id, ChargingStationDTO chargingStationDTO);

    @Transactional
    ChargingStationDTO updateChargingStation(Long id, ChargingStationRequestDTO chargingStationRequestDTO);

    void deleteChargingStation(Long id);

    List<ChargingStationDTO> getChargingStationsByAvailability(boolean isAvailable);

    List<ChargingStationDTO> getChargingStationsByLocation(Long locationId);
}