package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByCity(String city);

    List<Location> findByZipcode(String zipcode);
}
