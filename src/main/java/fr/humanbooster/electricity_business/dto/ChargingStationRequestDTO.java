package fr.humanbooster.electricity_business.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChargingStationRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 255, message = "Le nom doit contenir entre 2 et 255 caractères")
    private String name;

    @NotNull(message = "Le tarif horaire est obligatoire")
    @Min(value = 0, message = "Le tarif horaire doit être positif ou nul")
    private Double hourlyRate;

    @NotNull(message = "La puissance est obligatoire")
    @Min(value = 0, message = "La puissance doit être positive ou nulle")
    private Double power;

    @Size(max = 500, message = "Les instructions ne peuvent pas dépasser 500 caractères")
    private String instruction;

    private String picture;
    private String video;

    @NotNull(message = "Le statut de disponibilité est obligatoire")
    private Boolean isAvailable;

    @NotNull(message = "L'ID du propriétaire est obligatoire")
    private Long ownerId;

    @NotNull(message = "L'ID de la localisation est obligatoire")
    private Long locationId;

    public ChargingStationRequestDTO() {
    }

    public ChargingStationRequestDTO(String name, Double hourlyRate, Double power, String instruction, String picture,
                                     String video, Boolean isAvailable, Long ownerId, Long locationId) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.power = power;
        this.instruction = instruction;
        this.picture = picture;
        this.video = video;
        this.isAvailable = isAvailable;
        this.ownerId = ownerId;
        this.locationId = locationId;
    }

    // Getters et Setters
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

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "ChargingStationRequestDTO{" +
                "name='" + name + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", power=" + power +
                ", instruction='" + instruction + '\'' +
                ", picture='" + picture + '\'' +
                ", video='" + video + '\'' +
                ", isAvailable=" + isAvailable +
                ", ownerId=" + ownerId +
                ", locationId=" + locationId +
                '}';
    }
}
