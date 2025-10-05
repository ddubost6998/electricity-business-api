package fr.humanbooster.electricity_business.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void shouldRegisterAndRetrieveUserSuccessfully() throws Exception {
        // ARRANGE
        UserRegisterDTO registerDTO = new UserRegisterDTO(
                "Alice", "Martin", "alice.martin@gmail.com", "MyStrongPassword!2025",
                "0601020304", LocalDate.of(1998, 6, 27)
        );

        // ACT
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk());

        // ASSERT
        assertThat(userRepository.findByEmail("alice.martin@gmail.com")).isPresent();

        User savedUser = userRepository.findByEmail("alice.martin@gmail.com").get();
        assertThat(savedUser.getFirstname()).isEqualTo("Alice");
        assertThat(savedUser.getVerified()).isFalse();
    }

    @Test
    void shouldReturnUserList_whenUsersExist() throws Exception {
        // ARRANGE
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@gmail.com");
        user.setPassword("EncodedPassword123?");
        user.setPhone("0601020304");
        user.setBirthdate(LocalDate.of(1999, 3, 3));
        user.setVerified(true);
        user.setVerificationCode("abc123");
        userRepository.save(user);

        // ACT & ASSERT
        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("john.doe@gmail.com"));
    }
}
