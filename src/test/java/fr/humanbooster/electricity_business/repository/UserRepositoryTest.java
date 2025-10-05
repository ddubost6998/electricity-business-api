package fr.humanbooster.electricity_business.repository;

import fr.humanbooster.electricity_business.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Création d'un utilisateur de test réutilisable
    private User createValidUser() {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("Repo");
        user.setEmail("test.repo@example.com");
        user.setPassword("EncodedPassword123");
        user.setPhone("0708091011");
        user.setBirthdate(LocalDate.of(1990, 1, 1));
        user.setVerified(true);
        return user;
    }

    @Test
    void whenFindByEmail_thenReturnUser() {
        // ARRANGE
        User validUser = createValidUser();
        entityManager.persistAndFlush(validUser);

        // ACT
        Optional<User> found = userRepository.findByEmail(validUser.getEmail());

        // ASSERT
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo(validUser.getEmail());
    }

    @Test
    void whenSaveUserWithDuplicateEmail_thenThrowException() {
        // ARRANGE
        User user1 = createValidUser();
        entityManager.persistAndFlush(user1);

        User user2 = createValidUser();

        // ACT & ASSERT
        assertThrows(Exception.class, () -> entityManager.persistAndFlush(user2));
    }
}
