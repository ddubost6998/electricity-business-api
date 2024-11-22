package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.service.ChargingStationService;
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
    public ResponseEntity<ChargingStationDTO> createChargingStation(@RequestBody ChargingStationDTO chargingStationDTO) {
        ChargingStationDTO createdStation = chargingStationService.createChargingStation(chargingStationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStationDTO> getChargingStationById(@PathVariable Long id) {
        try {
            ChargingStationDTO chargingStation = chargingStationService.getChargingStationById(id);
            return ResponseEntity.ok(chargingStation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ChargingStationDTO>> getAllChargingStations() {
        List<ChargingStationDTO> chargingStations = chargingStationService.getAllChargingStations();
        return ResponseEntity.ok(chargingStations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChargingStationDTO> updateChargingStation(@PathVariable Long id, @RequestBody ChargingStationDTO chargingStationDTO) {
        try {
            ChargingStationDTO updatedStation = chargingStationService.updateChargingStation(id, chargingStationDTO);
            return ResponseEntity.ok(updatedStation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChargingStation(@PathVariable Long id) {
        try {
            chargingStationService.deleteChargingStation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
