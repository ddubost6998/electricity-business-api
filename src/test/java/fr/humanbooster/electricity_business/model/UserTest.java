package fr.humanbooster.electricity_business.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    // Crée un objet User de base pour la réutilisation
    private User createBaseUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setFirstname("Damien");
        user.setLastname("Dubost");
        user.setEmail("damien.dubost@gmail.com");
        user.setPassword("hashed-password");
        user.setBirthdate(LocalDate.of(1998, 6, 27));
        return user;
    }

    @Test
    void testGettersAndSetters() {
        // ARRANGE
        User user = new User();
        String expectedEmail = "test@test.com";

        // ACT
        user.setEmail(expectedEmail);

        // ASSERT
        assertEquals(expectedEmail, user.getEmail(), "Le getter doit retourner l'email qui vient d'être défini");
        assertNull(user.getId(), "L'ID doit être nul par défaut avant la sauvegarde");
    }

    @Test
    void testEqualityBasedOnId() {
        // ARRANGE
        User user1 = createBaseUser(1L);
        User user2 = createBaseUser(1L); // Même ID, propriétés différentes
        User user3 = createBaseUser(2L); // ID différent

        // ASSERT
        assertEquals(user1, user2, "Deux utilisateurs avec le même ID doivent être égaux");
        assertEquals(user1.hashCode(), user2.hashCode(), "Deux objets égaux doivent avoir le même hashCode");

        assertNotEquals(user1, user3, "Deux utilisateurs avec des ID différents ne doivent pas être égaux");
    }

    @Test
    void testEqualityWithNullAndDifferentType() {
        // ARRANGE
        User user = createBaseUser(1L);

        // ASSERT
        assertNotEquals(null, user, "Un objet n'est pas égal à null");

        assertNotEquals("A String", user, "Un User n'est pas égal à un autre type d'objet");
    }

    @Test
    void testToStringContent() {
        // ARRANGE
        User user = createBaseUser(1L);

        // ACT
        String userString = user.toString();

        // ASSERT
        assertTrue(userString.contains("id=1"), "Le toString doit contenir l'ID");
        assertTrue(userString.contains("email='damien.dubost@gmail.com'"), "Le toString doit contenir l'email");
        assertFalse(userString.contains("hashed-password"), "Le toString NE DOIT PAS contenir le mot de passe");
    }
}
