package fr.humanbooster.electricity_business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'adresse email n'est pas valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" + // Masquer le mot de passe
                '}';
    }
}