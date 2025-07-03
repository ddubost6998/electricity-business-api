package fr.humanbooster.electricity_business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class AddressRequestDTO {

    @NotBlank(message = "La rue est obligatoire")
    @Size(min = 3, max = 100, message = "La rue doit avoir entre 3 et 100 caractères")
    private String street;

    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(regexp = "^[0-9]{5}$", message = "Le code postal doit contenir 5 chiffres")
    private String zipcode;

    @NotBlank(message = "La ville est obligatoire")
    @Size(min = 2, max = 50, message = "La ville doit avoir entre 2 et 50 caractères")
    private String city;

    public AddressRequestDTO() {
    }

    public AddressRequestDTO(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressRequestDTO{" +
                "street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}