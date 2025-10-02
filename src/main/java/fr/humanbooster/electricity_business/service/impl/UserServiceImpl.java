package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.mapper.UserMapper;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.repository.UserRepository;
import fr.humanbooster.electricity_business.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailServiceImpl mailService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, MailServiceImpl mailService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mailService = mailService;
    }

    @Override
    public UserDTO registerUser(UserRegisterDTO userRegisterDTO) {

        User user = userMapper.toEntity(userRegisterDTO);

        String plainPassword = user.getPassword();
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty for registration.");
        }
        user.setPassword(BCrypt.hashpw(plainPassword, BCrypt.gensalt()));

        user.setVerified(false);
        user.setVerificationCode(generateVerificationCode());

        User savedUser = userRepository.save(user);

        mailService.sendEmail(
                savedUser.getEmail(),
                "Bienvenue chez Electricity Business",
                "Bonjour " + savedUser.getFirstname() + ",\n\n" +
                        "Merci de vous être inscrit sur notre plateforme. Nous sommes ravis de vous compter parmi nous.\n\n" +
                        "Cordialement,\nL'équipe Electricity Business."
        );

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO validateRegistration(String email, String validationCode) {
        return null;
    }

    @Override
    public UserDTO createUser(UserRegisterDTO userRegisterDTO) {

        User user = userMapper.toEntity(userRegisterDTO);

        String plainPassword = user.getPassword();
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty for creation.");
        }
        user.setPassword(BCrypt.hashpw(plainPassword, BCrypt.gensalt()));
        user.setVerified(false);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO loginUser(UserLoginDTO userLoginDTO) {

        Optional<User> userOptional = userRepository.findByEmail(userLoginDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userOptional.get();

        if (BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword())) {
            return userMapper.toDTO(user);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public void logoutUser(Long userId) {

    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
                existingUser.setFirstname(userDTO.getFirstname());
                existingUser.setLastname(userDTO.getLastname());
                existingUser.setEmail(userDTO.getEmail());
                existingUser.setPhone(userDTO.getPhone());

        if (userDTO.getBirthdate() != null) {
            existingUser.setBirthdate(userDTO.getBirthdate());
        }
        if (userDTO.getVerified() != null) {
            existingUser.setVerified(userDTO.getVerified());
        }


        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public UserDTO updateUserProfile(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}