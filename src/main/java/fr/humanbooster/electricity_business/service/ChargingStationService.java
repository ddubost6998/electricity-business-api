package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;

import java.util.List;

public interface ChargingStationService {

    ChargingStationDTO createChargingStation(ChargingStationDTO chargingStationDTO);

    ChargingStationDTO getChargingStationById(Long id);

    List<ChargingStationDTO> getAllChargingStations();

    ChargingStationDTO updateChargingStation(Long id, ChargingStationDTO chargingStationDTO);

    void deleteChargingStation(Long id);

    List<ChargingStationDTO> getChargingStationsByAvailability(boolean isAvailable);

    List<ChargingStationDTO> getChargingStationsByLocation(Long locationId);
}