package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.mapper.ChargingStationMapper;
import fr.humanbooster.electricity_business.model.ChargingStation;
import fr.humanbooster.electricity_business.repository.ChargingStationRepository;
import fr.humanbooster.electricity_business.service.impl.ChargingStationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChargingStationServiceImplTest {

    @InjectMocks
    private ChargingStationServiceImpl chargingStationService;

    @Mock
    private ChargingStationRepository chargingStationRepository;

    @Mock
    private ChargingStationMapper chargingStationMapper;

    @Test
    void shouldGetChargingStationById() {

        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId(1L);
        chargingStation.setName("Station Voltaire");

        ChargingStationDTO chargingStationDTO = new ChargingStationDTO();
        chargingStationDTO.setId(1L);
        chargingStationDTO.setName("Station Voltaire");

        Mockito.when(chargingStationRepository.findById(1L)).thenReturn(Optional.of(chargingStation));
        Mockito.when(chargingStationMapper.toDto(chargingStation)).thenReturn(chargingStationDTO);

        ChargingStationDTO result = chargingStationService.getChargingStationById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Station Voltaire");
        verify(chargingStationRepository, times(1)).findById(1L);
    }
}
