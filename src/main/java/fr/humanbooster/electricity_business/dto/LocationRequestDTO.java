package fr.humanbooster.electricity_business.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LocationRequestDTO {

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères")
    private String address;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100, message = "La ville ne peut pas dépasser 100 caractères")
    private String city;

    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(regexp = "^[0-9]{5}$", message = "Le code postal doit contenir 5 chiffres")
    private String zipcode;

    @NotNull(message = "La latitude est obligatoire")
    @DecimalMin(value = "-90.0", message = "La latitude doit être au minimum -90")
    @DecimalMax(value = "90.0", message = "La latitude doit être au maximum 90")
    private Double latitude;

    @NotNull(message = "La longitude est obligatoire")
    @DecimalMin(value = "-180.0", message = "La longitude doit être au minimum -180")
    @DecimalMax(value = "180.0", message = "La longitude doit être au maximum 180")
    private Double longitude;

    @NotBlank(message = "Le pays est obligatoire")
    @Size(max = 100, message = "Le pays ne peut pas dépasser 100 caractères")
    private String country;

    public LocationRequestDTO() {
    }

    public LocationRequestDTO(String address, String city, String zipcode, Double latitude, Double longitude, String country) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "LocationRequestDTO{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                '}';
    }
}
