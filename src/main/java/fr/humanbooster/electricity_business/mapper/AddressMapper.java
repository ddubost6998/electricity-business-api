package fr.humanbooster.electricity_business.mapper;

import fr.humanbooster.electricity_business.dto.AddressDTO;
import fr.humanbooster.electricity_business.dto.AddressRequestDTO;
import fr.humanbooster.electricity_business.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toDto(Address address);

    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressRequestDTO addressRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(AddressRequestDTO addressRequestDTO, @MappingTarget Address address);
}
