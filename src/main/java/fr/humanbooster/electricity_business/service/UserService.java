package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import java.util.List;

public interface UserService {

    UserDTO registerUser(UserRegisterDTO userRegisterDTO);

    UserDTO createUser(UserRegisterDTO userRegisterDTO);

    UserDTO loginUser(UserLoginDTO userLoginDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}