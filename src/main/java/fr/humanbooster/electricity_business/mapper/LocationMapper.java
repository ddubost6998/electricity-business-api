package fr.humanbooster.electricity_business.mapper;

import fr.humanbooster.electricity_business.dto.LocationDTO;
import fr.humanbooster.electricity_business.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationDTO toDTO(Location entity) {
        if (entity == null) {
            return null;
        }

        return new LocationDTO(
                entity.getId(),
                entity.getAddress(),
                entity.getCity(),
                entity.getZipcode(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCountry()
        );
    }

    public Location toEntity(LocationDTO dto) {
        if (dto == null) {
            return null;
        }

        Location location = new Location();
        location.setId(dto.getId());
        location.setAddress(dto.getAddress());
        location.setCity(dto.getCity());
        location.setZipcode(dto.getZipcode());
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setCountry(dto.getCountry());

        return location;
    }
}
