package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
