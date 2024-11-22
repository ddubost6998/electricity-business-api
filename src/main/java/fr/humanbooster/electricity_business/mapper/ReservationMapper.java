package fr.humanbooster.electricity_business.mapper;

import org.springframework.stereotype.Component;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.model.Reservation;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation entity) {
        if (entity == null) {
            return null;
        }

        return new ReservationDTO(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getChargingStation() != null ? entity.getChargingStation().getId() : null,
            entity.getStartTime(),
            entity.getEndTime(),
            entity.getTotalPrice(),
            entity.getStatus() != null ? ReservationDTO.ReservationStatus.valueOf(entity.getStatus().name()) : null
        );
    }

    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) {
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setTotalPrice(dto.getTotalPrice());

        if (dto.getStatus() != null) {
            reservation.setStatus(Reservation.ReservationStatus.valueOf(dto.getStatus().name()));
        }

        return reservation;
    }
}
