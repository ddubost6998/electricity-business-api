package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.mapper.LocationMapper;
import fr.humanbooster.electricity_business.model.Location;
import fr.humanbooster.electricity_business.repository.LocationRepository;
import fr.humanbooster.electricity_business.service.LocationService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = locationMapper.toEntity(locationDTO);
        Location savedLocation = locationRepository.save(location);
        return locationMapper.toDTO(savedLocation);
    }

    @Override
    public LocationDTO getLocationById(Long id) {
        return locationRepository.findById(id)
                .map(locationMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));

        existingLocation.setAddress(locationDTO.getAddress());
        existingLocation.setCity(locationDTO.getCity());
        existingLocation.setZipcode(locationDTO.getZipcode());
        existingLocation.setLatitude(locationDTO.getLatitude());
        existingLocation.setLongitude(locationDTO.getLongitude());
        existingLocation.setCountry(locationDTO.getCountry());

        Location updatedLocation = locationRepository.save(existingLocation);
        return locationMapper.toDTO(updatedLocation);
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        locationRepository.delete(location);
    }
}
