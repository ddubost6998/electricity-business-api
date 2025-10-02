package fr.humanbooster.electricity_business.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 255)
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100)
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(regexp = "^[0-9]{5}$", message = "Le code postal doit contenir 5 chiffres")
    private String zipcode;

    @Column(nullable = false)
    @NotNull(message = "La latitude est obligatoire")
    @DecimalMin(value = "-90.0", message = "La latitude doit être au minimum -90")
    @DecimalMax(value = "90.0", message = "La latitude doit être au maximum 90")
    private Double latitude;

    @Column(nullable = false)
    @NotNull(message = "La longitude est obligatoire")
    @DecimalMin(value = "-180.0", message = "La longitude doit être au minimum -180")
    @DecimalMax(value = "180.0", message = "La longitude doit être au maximum 180")
    private Double longitude;

    @Column(nullable = false)
    @NotBlank(message = "Le pays est obligatoire")
    @Size(max = 100)
    private String country;

    @Column(nullable = false, updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime updatedAt;

    public Location() {
    }

    public Location(String address, String city, String zipcode, Double latitude, Double longitude, String country) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public Location(Long id, String address, String city, String zipcode, Double latitude, Double longitude, String country, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
