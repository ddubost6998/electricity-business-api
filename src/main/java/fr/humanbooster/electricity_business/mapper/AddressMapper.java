package fr.humanbooster.electricity_business.mapper;

import org.springframework.stereotype.Component;
import fr.humanbooster.electricity_business.dto.AddressDTO;
import fr.humanbooster.electricity_business.model.Address;

@Component
public class AddressMapper {

    public AddressDTO toDTO(Address entity) {
        if (entity == null) {
            return null;
        }

        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setZipcode(entity.getZipcode());
        return dto;
    }

    public Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setZipcode(dto.getZipcode());
        return entity;
    }
}
