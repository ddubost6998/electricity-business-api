package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.mapper.ChargingStationMapper;
import fr.humanbooster.electricity_business.model.ChargingStation;
import fr.humanbooster.electricity_business.repository.ChargingStationRepository;
import fr.humanbooster.electricity_business.service.ChargingStationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final ChargingStationMapper chargingStationMapper;

    public ChargingStationServiceImpl(ChargingStationRepository chargingStationRepository, ChargingStationMapper chargingStationMapper) {
        this.chargingStationRepository = chargingStationRepository;
        this.chargingStationMapper = chargingStationMapper;
    }

    @Override
    public ChargingStationDTO createChargingStation(ChargingStationDTO chargingStationDTO) {
        ChargingStation chargingStation = chargingStationMapper.toEntity(chargingStationDTO);
        ChargingStation savedStation = chargingStationRepository.save(chargingStation);
        return chargingStationMapper.toDTO(savedStation);
    }

    @Override
    public ChargingStationDTO getChargingStationById(Long id) {
        return chargingStationRepository.findById(id)
                .map(chargingStationMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Charging station not found with id: " + id));
    }

    @Override
    public List<ChargingStationDTO> getAllChargingStations() {
        return chargingStationRepository.findAll().stream()
                .map(chargingStationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChargingStationDTO updateChargingStation(Long id, ChargingStationDTO chargingStationDTO) {
        ChargingStation existingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Charging station not found with id: " + id));

        existingStation.setName(chargingStationDTO.getName());
        existingStation.setHourlyRate(chargingStationDTO.getHourlyRate());
        existingStation.setPower(chargingStationDTO.getPower());
        existingStation.setInstruction(chargingStationDTO.getInstruction());
        existingStation.setIsAvailable(chargingStationDTO.getIsAvailable());

        ChargingStation updatedStation = chargingStationRepository.save(existingStation);
        return chargingStationMapper.toDTO(updatedStation);
    }

    @Override
    public void deleteChargingStation(Long id) {
        ChargingStation chargingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Charging station not found with id: " + id));
        chargingStationRepository.delete(chargingStation);
    }

    @Override
    public List<ChargingStationDTO> getChargingStationsByAvailability(boolean isAvailable) {
        return chargingStationRepository.findByIsAvailable(isAvailable).stream()
                .map(chargingStationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChargingStationDTO> getChargingStationsByLocation(Long locationId) {
        return chargingStationRepository.findByLocationId(locationId).stream()
                .map(chargingStationMapper::toDTO)
                .collect(Collectors.toList());
    }
}