package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.mapper.UserMapper;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.repository.UserRepository;
import fr.humanbooster.electricity_business.service.impl.MailServiceImpl;
import fr.humanbooster.electricity_business.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private MailServiceImpl mailService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        UserRegisterDTO registerDTO = new UserRegisterDTO(
                "John", "Doe", "john.doe@example.com", "MySuperStrongP@ssw0rd", "0612345678", LocalDate.of(1990, 1, 1)
        );

        User userToSave = new User();
        userToSave.setFirstname(registerDTO.getFirstname());
        userToSave.setLastname(registerDTO.getLastname());
        userToSave.setEmail(registerDTO.getEmail());
        userToSave.setPassword(registerDTO.getPassword());
        userToSave.setPhone(registerDTO.getPhone());
        userToSave.setBirthdate(registerDTO.getBirthdate());

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstname(registerDTO.getFirstname());
        savedUser.setLastname(registerDTO.getLastname());
        savedUser.setEmail(registerDTO.getEmail());
        savedUser.setPassword(BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt()));
        savedUser.setPhone(registerDTO.getPhone());
        savedUser.setBirthdate(registerDTO.getBirthdate());
        savedUser.setVerified(false);
        savedUser.setVerificationCode("some-uuid-code");

        UserDTO expectedResponseDTO = new UserDTO(
                1L, "John", "Doe", "john.doe@example.com", "0612345678", LocalDate.of(1990, 1, 1), false
        );

        when(userMapper.toEntity(any(UserRegisterDTO.class))).thenReturn(userToSave);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userMapper.toDTO(any(User.class))).thenReturn(expectedResponseDTO);
        doNothing().when(mailService).sendEmail(anyString(), anyString(), anyString());

        UserDTO resultDTO = userService.registerUser(registerDTO);

        assertNotNull(resultDTO);
        assertEquals(expectedResponseDTO.getId(), resultDTO.getId());
        assertEquals(expectedResponseDTO.getFirstname(), resultDTO.getFirstname());
        assertEquals(expectedResponseDTO.getEmail(), resultDTO.getEmail());

        verify(userRepository, times(1)).save(any(User.class));

        verify(mailService, times(1)).sendEmail(eq(savedUser.getEmail()), anyString(), anyString());

        verify(userMapper, times(1)).toDTO(any(User.class));
    }

    @Test
    void shouldLoginUserSuccessfully() {
        UserLoginDTO loginDTO = new UserLoginDTO("test@example.com", "CorrectPassword123!");

        User foundUser = new User();
        foundUser.setId(1L);
        foundUser.setEmail("test@example.com");
        foundUser.setPassword(BCrypt.hashpw("CorrectPassword123!", BCrypt.gensalt()));
        foundUser.setVerified(true);

        UserDTO expectedResponseDTO = new UserDTO(
                1L, "Test", "User", "test@example.com", "0123456789", LocalDate.of(1985, 1, 1), true
        );

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(foundUser));
        when(userMapper.toDTO(foundUser)).thenReturn(expectedResponseDTO);

        UserDTO resultDTO = userService.loginUser(loginDTO);

        assertNotNull(resultDTO);
        assertEquals(expectedResponseDTO.getEmail(), resultDTO.getEmail());
        verify(userRepository, times(1)).findByEmail(loginDTO.getEmail());
        verify(userMapper, times(1)).toDTO(foundUser);
    }

    @Test
    void shouldThrowExceptionWhenLoginWithInvalidCredentials() {
        // Arrange
        UserLoginDTO loginDTO = new UserLoginDTO("test@example.com", "WrongPassword");

        User foundUser = new User();
        foundUser.setId(1L);
        foundUser.setEmail("test@example.com");
        foundUser.setPassword(BCrypt.hashpw("CorrectPassword123!", BCrypt.gensalt()));

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(foundUser));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.loginUser(loginDTO);
        });
        assertEquals("Invalid credentials", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(loginDTO.getEmail());
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("hashedpassword");
        user.setPhone("123456789");
        user.setBirthdate(LocalDate.of(1980, 1, 1));
        user.setVerified(true);

        UserDTO userDTO = new UserDTO(1L, "John", "Doe", "john.doe@example.com", "123456789", LocalDate.of(1980, 1, 1), true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
        assertEquals("john.doe@example.com", result.getEmail()); // Vérifier aussi l'email
        assertEquals("123456789", result.getPhone());
        assertEquals(LocalDate.of(1980, 1, 1), result.getBirthdate());
        assertTrue(result.getVerified()); // Vérifier le statut de vérification
        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toDTO(user); // Vérifier l'appel au mapper
    }

    @Test
    void shouldGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(1L); user1.setFirstname("John"); user1.setEmail("john@example.com");
        User user2 = new User();
        user2.setId(2L); user2.setFirstname("Jane"); user2.setEmail("jane@example.com");
        List<User> users = Arrays.asList(user1, user2);

        UserDTO userDTO1 = new UserDTO(1L, "John", "Doe", "john@example.com", "0123456789", LocalDate.of(1980,1,1), false);
        UserDTO userDTO2 = new UserDTO(2L, "Jane", "Doe", "jane@example.com", "0987654321", LocalDate.of(1985,5,5), true);
        List<UserDTO> expectedDTOs = Arrays.asList(userDTO1, userDTO2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDTO(user1)).thenReturn(userDTO1);
        when(userMapper.toDTO(user2)).thenReturn(userDTO2);

        List<UserDTO> resultDTOs = userService.getAllUsers();

        assertNotNull(resultDTOs);
        assertEquals(2, resultDTOs.size());
        assertEquals("John", resultDTOs.get(0).getFirstname());
        assertEquals("jane@example.com", resultDTOs.get(1).getEmail());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(2)).toDTO(any(User.class));
    }
}
