package fr.humanbooster.electricity_business.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private Long id;

    private Long userId;

    private Long chargingStationId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double totalPrice;

    private ReservationStatus status;

    public enum ReservationStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        COMPLETED
    }

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Long userId, Long chargingStationId, LocalDateTime startTime, LocalDateTime endTime,
                          Double totalPrice, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.chargingStationId = chargingStationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.status = status;
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
                '}';
    }
}
