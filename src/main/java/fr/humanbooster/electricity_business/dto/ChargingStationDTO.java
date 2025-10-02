package fr.humanbooster.electricity_business.dto;

import java.time.LocalDateTime;

public class ChargingStationDTO {

    private Long id;
    private String name;
    private Double hourlyRate;
    private Double power;
    private String instruction;
    private String picture;
    private String video;
    private Boolean isAvailable;
    private UserDTO owner;
    private LocationDTO location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChargingStationDTO() {
    }

    public ChargingStationDTO(Long id, String name, Double hourlyRate, Double power, String instruction,
                              String picture, String video, Boolean isAvailable, UserDTO owner, LocationDTO location,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
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

    @Override
    public String toString() {
        return "ChargingStationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", power=" + power +
                ", instruction='" + instruction + '\'' +
                ", picture='" + picture + '\'' +
                ", video='" + video + '\'' +
                ", isAvailable=" + isAvailable +
                ", owner=" + owner +
                ", location=" + location +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
