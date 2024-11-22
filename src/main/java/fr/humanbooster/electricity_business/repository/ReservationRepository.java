package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.Reservation;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.model.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    List<Reservation> findByChargingStation(ChargingStation chargingStation);

    List<Reservation> findByStatus(Reservation.ReservationStatus status);

    Optional<Reservation> findByUserId(Long userId);

    Optional<Reservation> findByChargingStationId(Long chargingStationId);
}
