package fr.humanbooster.electricity_business.mapper;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.dto.LocationRequestDTO;
import fr.humanbooster.electricity_business.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDTO toDto(Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Location toEntity(LocationRequestDTO requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequestDto(LocationRequestDTO requestDto, @MappingTarget Location location);
}
