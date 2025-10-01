package fr.humanbooster.electricity_business.mapper;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.dto.ReservationRequestDTO;
import fr.humanbooster.electricity_business.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "chargingStation.id", target = "chargingStationId")
    ReservationDTO toDto(Reservation reservation);

    @Mapping(target = "status", source = "chargingStationId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "chargingStation", ignore = true)
    Reservation toEntity(ReservationRequestDTO requestDto);

    @Mapping(target = "status", source = "chargingStationId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "chargingStation", ignore = true)
    void updateEntityFromRequestDto(ReservationRequestDTO requestDto, @MappingTarget Reservation reservation);

}
