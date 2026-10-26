package com.rides.ride_booking_Application.Dto;

import com.rides.ride_booking_Application.roles.RidesStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RideResponse {
    private Long rideId;
    @Enumerated(EnumType.STRING)
    private RidesStatus status;
    private String pickupLocation;
    private String dropLocation;
    private Double fare;
private Double distanceKm;
    private Long duration;
    private String customerName;
    private String driverName;      // accept se pehle null
    private String driverPhone;     // accept se pehle null
    private String vehicleNumber;
}
