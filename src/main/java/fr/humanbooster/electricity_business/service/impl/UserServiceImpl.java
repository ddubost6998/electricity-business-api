package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.mapper.UserMapper;
import fr.humanbooster.electricity_business.model.User;
import fr.humanbooster.electricity_business.repository.UserRepository;
import fr.humanbooster.electricity_business.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public UserDTO registerUser(UserRegisterDTO registerDTO) {
        User user = userMapper.toEntity(registerDTO);

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setVerified(false);
        user.setVerificationCode(generateVerificationCode());

        User savedUser = userRepository.save(user);

        // Envoi de l'email de bienvenue et de validation
        mailService.sendEmail(
                savedUser.getEmail(),
                "Bienvenue chez Electricity Business",
                "Bonjour " + savedUser.getFirstname() + ",\n\n" +
                        "Merci de vous être inscrit sur notre plateforme. Veuillez valider votre compte en utilisant ce code : " +
                        savedUser.getVerificationCode() +
                        "\n\nCordialement,\nL'équipe Electricity Business."
        );

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO validateRegistration(String email, String verificationCode) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet email"));

        if (!verificationCode.equals(user.getVerificationCode())) {
            throw new RuntimeException("Code de vérification invalide");
        }

        user.setVerified(true);
        user.setVerificationCode(null);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO createUser(UserRegisterDTO userRegisterDTO) {
        User user = userMapper.toEntity(userRegisterDTO);

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setVerified(false);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO loginUser(UserLoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Identifiants invalides"));

        if (Boolean.FALSE.equals(user.getVerified())) {
            throw new RuntimeException("Utilisateur non vérifié. Veuillez valider votre email.");
        } else {
            if (!BCrypt.checkpw(loginDTO.getPassword(), user.getPassword())) {
                throw new RuntimeException("Identifiants invalides");
            }

            return userMapper.toDTO(user);
        }

    }

    @Override
    public void logoutUser(Long userId) {
        // Optionnelle, car pour JWT la déconnexion côté serveur n'est pas obligatoire
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id: " + id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id: " + id));

        existingUser.setFirstname(userDTO.getFirstname());
        existingUser.setLastname(userDTO.getLastname());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        if (userDTO.getBirthdate() != null) existingUser.setBirthdate(userDTO.getBirthdate());
        if (userDTO.getVerified() != null) existingUser.setVerified(userDTO.getVerified());

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public UserDTO updateUserProfile(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id: " + id));

        existingUser.setFirstname(userDTO.getFirstname());
        existingUser.setLastname(userDTO.getLastname());
        existingUser.setPhone(userDTO.getPhone());
        if (userDTO.getBirthdate() != null) existingUser.setBirthdate(userDTO.getBirthdate());

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id: " + id));
        userRepository.delete(user);
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString();
    }
}
