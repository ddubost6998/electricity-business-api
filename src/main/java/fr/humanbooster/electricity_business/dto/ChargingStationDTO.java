package fr.humanbooster.electricity_business.dto;

public class ChargingStationDTO {

    private Long id;

    private String name;

    private Double hourlyRate;

    private Double power;

    private String instruction;

    private Boolean isAvailable;

    private UserDTO owner;

    private LocationDTO location;

    public ChargingStationDTO() {
    }

    public ChargingStationDTO(Long id, String name, Double hourlyRate, Double power, LocationDTO location) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.power = power;
        this.location = location;
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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
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

    @Override
    public String toString() {
        return "ChargingStationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", power=" + power +
                ", instruction='" + instruction + '\'' +
                ", isAvailable=" + isAvailable +
                ", owner=" + owner +
                ", location=" + location +
                '}';
    }
}
