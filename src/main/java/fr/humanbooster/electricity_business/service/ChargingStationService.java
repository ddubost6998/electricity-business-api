package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.dto.ChargingStationRequestDTO; // Assurez-vous d'utiliser RequestDTO

import java.util.List;

public interface ChargingStationService {

    ChargingStationDTO createChargingStation(ChargingStationRequestDTO chargingStationRequestDTO);

    ChargingStationDTO getChargingStationById(Long id);

    List<ChargingStationDTO> getAllChargingStations();

    ChargingStationDTO updateChargingStation(Long id, ChargingStationRequestDTO chargingStationRequestDTO);

    void deleteChargingStation(Long id);

    List<ChargingStationDTO> getChargingStationsByAvailability(boolean isAvailable);

    List<ChargingStationDTO> getChargingStationsByLocation(Long locationId);

    ChargingStationDTO setHourlyRate(Long id, Double newHourlyRate);

}