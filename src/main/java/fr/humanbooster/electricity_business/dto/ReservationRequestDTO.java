package fr.humanbooster.electricity_business.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.time.LocalDateTime;

public class ReservationRequestDTO {

    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    private Long userId;

    @NotNull(message = "L'ID de la station de recharge est obligatoire")
    private Long chargingStationId;

    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur")
    private LocalDateTime startTime;

    @NotNull(message = "La date de fin est obligatoire")
    @Future(message = "La date de fin doit être dans le futur")
    private LocalDateTime endTime;

    @NotNull(message = "Le prix total est obligatoire")
    @DecimalMin(value = "0.0", message = "Le prix total doit être positif ou nul")
    private Double totalPrice;

    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(Long userId, Long chargingStationId, LocalDateTime startTime, LocalDateTime endTime, Double totalPrice) {
        this.userId = userId;
        this.chargingStationId = chargingStationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChargingStationId() {
        return chargingStationId;
    }

    public void setChargingStationId(Long chargingStationId) {
        this.chargingStationId = chargingStationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ReservationRequestDTO{" +
                "userId=" + userId +
                ", chargingStationId=" + chargingStationId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
