package fr.humanbooster.electricity_business.mapper;

import fr.humanbooster.electricity_business.dto.ChargingStationDTO;
import fr.humanbooster.electricity_business.dto.ChargingStationRequestDTO;
import fr.humanbooster.electricity_business.model.ChargingStation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LocationMapper.class})
public interface ChargingStationMapper {

    ChargingStationDTO toDto(ChargingStation chargingStation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "location", ignore = true)
    ChargingStation toEntity(ChargingStationRequestDTO requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "location", ignore = true)
    void updateEntityFromRequestDto(ChargingStationRequestDTO requestDto, @MappingTarget ChargingStation chargingStation);
}
