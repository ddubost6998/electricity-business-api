package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.dto.LocationRequestDTO;
import fr.humanbooster.electricity_business.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationRestController {

    private final LocationService locationService;

    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@Valid @RequestBody LocationRequestDTO locationRequestDTO) {
        LocationDTO createdLocation = locationService.createLocation(locationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        LocationDTO location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Long id, @Valid @RequestBody LocationRequestDTO locationRequestDTO) {
        LocationDTO updatedLocation = locationService.updateLocation(id, locationRequestDTO);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
