package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.dto.ChargingStationRequestDTO;
import fr.humanbooster.electricity_business.service.ChargingStationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charging-stations")
public class ChargingStationRestController {

    private final ChargingStationService chargingStationService;

    public ChargingStationRestController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @PostMapping
    public ResponseEntity<ChargingStationDTO> createChargingStation(@Valid @RequestBody ChargingStationRequestDTO chargingStationRequestDTO) {
        ChargingStationDTO createdStation = chargingStationService.createChargingStation(chargingStationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStationDTO> getChargingStationById(@PathVariable Long id) {
        ChargingStationDTO station = chargingStationService.getChargingStationById(id);
        return ResponseEntity.ok(station);
    }

    @GetMapping
    public ResponseEntity<List<ChargingStationDTO>> getAllChargingStations() {
        List<ChargingStationDTO> stations = chargingStationService.getAllChargingStations();
        return ResponseEntity.ok(stations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChargingStationDTO> updateChargingStation(@PathVariable Long id, @Valid @RequestBody ChargingStationRequestDTO chargingStationRequestDTO) {
        ChargingStationDTO updatedStation = chargingStationService.updateChargingStation(id, chargingStationRequestDTO);
        return ResponseEntity.ok(updatedStation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChargingStation(@PathVariable Long id) {
        chargingStationService.deleteChargingStation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<ChargingStationDTO>> getAvailableChargingStations(@RequestParam(defaultValue = "true") boolean isAvailable) {
        List<ChargingStationDTO> stations = chargingStationService.getChargingStationsByAvailability(isAvailable);
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<ChargingStationDTO>> getChargingStationsByLocation(@PathVariable Long locationId) {
        List<ChargingStationDTO> stations = chargingStationService.getChargingStationsByLocation(locationId);
        return ResponseEntity.ok(stations);
    }

    @PatchMapping("/{id}/hourly-rate")
    public ResponseEntity<ChargingStationDTO> setHourlyRate(@PathVariable Long id, @RequestParam Double newRate) {
        ChargingStationDTO updatedStation = chargingStationService.setHourlyRate(id, newRate);
        return ResponseEntity.ok(updatedStation);
    }

    @GetMapping("/nearby-available")
    public ResponseEntity<List<ChargingStationDTO>> findAvailableChargingStationsNear(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(defaultValue = "5") Double radiusInKm) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}