package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** Inscription */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterDTO registerDTO) {
        UserDTO createdUser = userService.registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /** Validation d’un compte utilisateur via email et code de vérification */
    @PostMapping("/validate")
    public ResponseEntity<UserDTO> validateUser(
            @RequestParam String email,
            @RequestParam String code) {
        UserDTO validatedUser = userService.validateRegistration(email, code);
        return ResponseEntity.ok(validatedUser);
    }

    /** Connexion */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        UserDTO loggedInUser = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loggedInUser);
    }

    /** Déconnexion */
    @PostMapping("/{id}/logout")
    public ResponseEntity<Void> logoutUser(@PathVariable Long id) {
        userService.logoutUser(id);
        return ResponseEntity.noContent().build();
    }

    /** Récupère tous les utilisateurs */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /** Récupère un utilisateur par son ID */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /** Met à jour complètement un utilisateur */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /** Met à jour uniquement le profil utilisateur connecté */
    @PatchMapping("/{id}/profile")
    public ResponseEntity<UserDTO> updateUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedProfile = userService.updateUserProfile(id, userDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    /** Supprime un utilisateur */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
