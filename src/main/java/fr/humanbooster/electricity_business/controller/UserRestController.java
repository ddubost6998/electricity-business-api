package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.UserDTO;
import fr.humanbooster.electricity_business.dto.UserRegisterDTO;
import fr.humanbooster.electricity_business.dto.UserLoginDTO;
import fr.humanbooster.electricity_business.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserDTO createdUser = userService.registerUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/register/validate")
    public ResponseEntity<UserDTO> validateRegistration(@RequestParam String email, @RequestParam String code) {
        UserDTO validatedUser = userService.validateRegistration(email, code);
        return ResponseEntity.ok(validatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        UserDTO loggedInUser = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(loggedInUser);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUserProfile(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
