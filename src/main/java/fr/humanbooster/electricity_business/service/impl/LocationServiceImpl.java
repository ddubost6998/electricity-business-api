package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.dto.LocationRequestDTO;
import fr.humanbooster.electricity_business.mapper.LocationMapper;
import fr.humanbooster.electricity_business.model.Location;
import fr.humanbooster.electricity_business.repository.LocationRepository;
import fr.humanbooster.electricity_business.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Transactional
    @Override
    public LocationDTO createLocation(LocationRequestDTO locationRequestDTO) {
        Location location = locationMapper.toEntity(locationRequestDTO);
        Location savedLocation = locationRepository.save(location);
        return locationMapper.toDto(savedLocation);
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        return null;
    }

    @Override
    public LocationDTO getLocationById(Long id) {
        return locationRepository.findById(id)
                .map(locationMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Location non trouvée avec l'ID : " + id));
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        return null;
    }

    @Transactional
    @Override
    public LocationDTO updateLocation(Long id, LocationRequestDTO locationRequestDTO) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée avec l'ID : " + id));

        locationMapper.updateEntityFromRequestDto(locationRequestDTO, existingLocation);

        Location updatedLocation = locationRepository.save(existingLocation);
        return locationMapper.toDto(updatedLocation);
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location non trouvée avec l'ID : " + id);
        }
        locationRepository.deleteById(id);
    }
}