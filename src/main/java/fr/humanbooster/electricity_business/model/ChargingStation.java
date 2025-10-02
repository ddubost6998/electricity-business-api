package fr.humanbooster.electricity_business.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 255)
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Le tarif horaire est obligatoire")
    @Min(value = 0, message = "Le tarif horaire doit être positif ou nul")
    private Double hourlyRate;

    @Column(nullable = false)
    @NotNull(message = "La puissance est obligatoire")
    @Min(value = 0, message = "La puissance doit être positive ou nulle")
    private Double power;

    @Column(length = 500)
    @Size(max = 500, message = "Les instructions ne peuvent pas dépasser 500 caractères")
    private String instruction;

    private String picture;

    private String video;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Le propriétaire est obligatoire")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @NotNull(message = "La localisation est obligatoire")
    private Location location;

    @Column(nullable = false, updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime updatedAt;

    public ChargingStation() {
    }

    public ChargingStation(String name, Double hourlyRate, Double power, String video, Location location) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.power = power;
        this.video = video;
        this.location = location;
    }

    public ChargingStation(Long id, String name, Double hourlyRate, Double power, String instruction, String picture,
                           String video, Boolean isAvailable, User owner, Location location, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.power = power;
        this.instruction = instruction;
        this.picture = picture;
        this.video = video;
        this.isAvailable = isAvailable;
        this.owner = owner;
        this.location = location;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
