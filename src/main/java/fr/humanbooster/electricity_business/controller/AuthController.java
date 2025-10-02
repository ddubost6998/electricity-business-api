package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Endpoint pour l'authentification
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /** Enregistrement d'un nouvel utilisateur (création de l'entité) */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserDTO createdUser = userService.registerUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /** Validation de l'enregistrement (par email/code) */
    @PostMapping("/register/validate")
    public ResponseEntity<UserDTO> validateRegistration(@RequestParam String email, @RequestParam String code) {
        UserDTO validatedUser = userService.validateRegistration(email, code);
        return ResponseEntity.ok(validatedUser);
    }

    /**
     * Connexion de l'utilisateur (génère un token JWT)
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        UserDTO response = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(response);
    }
}
