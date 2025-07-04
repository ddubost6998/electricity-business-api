package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.Reservation;
import fr.humanbooster.electricity_business.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByChargingStationId(Long chargingStationId);

    List<Reservation> findByUserIdAndEndTimeAfterAndStatusIn(Long userId, LocalDateTime currentTime, List<ReservationStatus> statuses);

    List<Reservation> findByUserIdAndEndTimeBefore(Long userId, LocalDateTime currentTime);

    List<Reservation> findByUserIdAndChargingStationIdAndEndTimeBefore(Long userId, Long chargingStationId, LocalDateTime currentTime);
    List<Reservation> findByUserIdAndStartTimeBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByChargingStationId(Long chargingStationId);
}
