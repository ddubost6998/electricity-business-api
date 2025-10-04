package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.Role;
import fr.humanbooster.electricity_business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByVerificationCode(String verificationCode);

    Optional<User> findByEmailAndVerifiedTrue(String email);

    List<User> findAllByVerifiedTrue();

    List<User> findAllByVerifiedFalse();

    List<User> findAllByRole(Role role);
}
