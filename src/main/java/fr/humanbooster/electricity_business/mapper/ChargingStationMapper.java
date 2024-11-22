package fr.humanbooster.electricity_business.mapper;

import org.springframework.stereotype.Component;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.model.ChargingStation;

@Component
public class ChargingStationMapper {

    public ChargingStation toEntity(ChargingStationDTO dto) {
        if (dto == null) {
            return null;
        }

        ChargingStation station = new ChargingStation();
        station.setId(dto.getId());
        station.setName(dto.getName());
        station.setHourlyRate(dto.getHourlyRate());
        station.setPower(dto.getPower());
        station.setInstruction(dto.getInstruction());
        station.setIsAvailable(dto.getIsAvailable());
        return station;
    }

    public ChargingStationDTO toDTO(ChargingStation entity) {
        if (entity == null) {
            return null;
        }

        return new ChargingStationDTO(
        );
    }
}
