package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.ChargingStation;
import fr.humanbooster.electricity_business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

    List<ChargingStation> findByIsAvailableTrue();

    List<ChargingStation> findByOwner(User owner);

    Optional<ChargingStation> findByIsAvailable(boolean isAvailable);

    Optional<ChargingStation> findByLocationId(Long locationId);
}
