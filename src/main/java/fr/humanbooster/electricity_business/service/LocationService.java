package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.LocationDTO;

import java.util.List;

public interface LocationService {

    LocationDTO createLocation(LocationDTO locationDTO);

    LocationDTO getLocationById(Long id);

    List<LocationDTO> getAllLocations();

    LocationDTO updateLocation(Long id, LocationDTO locationDTO);

    void deleteLocation(Long id);
}
