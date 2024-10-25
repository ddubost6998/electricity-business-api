package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCity(String city);

    List<Address> findByZipcode(String zipcode);

    List<Address> findByCityAndZipcode(String city, String zipcode);
}
