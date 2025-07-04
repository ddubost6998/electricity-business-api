package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.mapper.LocationMapper;
import fr.humanbooster.electricity_business.model.Location;
import fr.humanbooster.electricity_business.repository.LocationRepository;
import fr.humanbooster.electricity_business.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private LocationMapper locationMapper;

    @Test
    void shouldGetAllLocations() {
        Location location = new Location();
        location.setId(1L);
        location.setCity("Lyon");

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.setCity("Lyon");

        Mockito.when(locationRepository.findAll()).thenReturn(List.of(location));
        Mockito.when(locationMapper.toDto(location)).thenReturn(locationDTO);

        List<LocationDTO> results = locationService.getAllLocations();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getCity()).isEqualTo("Lyon");
        verify(locationRepository, times(1)).findAll();
    }
}
