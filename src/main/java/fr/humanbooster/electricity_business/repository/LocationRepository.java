package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByCity(String city);
}
