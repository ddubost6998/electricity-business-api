package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserRegisterDTO registerDTO);

    UserDTO validateRegistration(String email, String validationCode);

    UserDTO createUser(UserRegisterDTO userRegisterDTO);

    UserDTO loginUser(UserLoginDTO loginDTO);
    void logoutUser(Long userId);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    UserDTO updateUserProfile(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}