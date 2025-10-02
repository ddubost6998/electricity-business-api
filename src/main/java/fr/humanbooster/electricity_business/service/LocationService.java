package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.dto.LocationRequestDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LocationService {

    @Transactional
    LocationDTO createLocation(LocationRequestDTO locationRequestDTO);

    LocationDTO createLocation(LocationDTO locationDTO);

    LocationDTO getLocationById(Long id);

    List<LocationDTO> getAllLocations();

    LocationDTO updateLocation(Long id, LocationDTO locationDTO);

    @Transactional
    LocationDTO updateLocation(Long id, LocationRequestDTO locationRequestDTO);

    void deleteLocation(Long id);
}
