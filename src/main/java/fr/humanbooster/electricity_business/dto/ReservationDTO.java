package fr.humanbooster.electricity_business.dto;

import fr.humanbooster.electricity_business.model.ReservationStatus;
import java.time.LocalDateTime;

public class ReservationDTO {

    private Long id;
    private Long userId;
    private Long chargingStationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalPrice;
    private ReservationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Long userId, Long chargingStationId, LocalDateTime startTime, LocalDateTime endTime,
                          Double totalPrice, ReservationStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.chargingStationId = chargingStationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
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
        return "ReservationDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", chargingStationId=" + chargingStationId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
