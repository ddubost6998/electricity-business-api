package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

    List<ChargingStation> findByIsAvailable(Boolean isAvailable);

    List<ChargingStation> findByLocationId(Long locationId);

    List<ChargingStation> findByOwnerId(Long ownerId);

}
