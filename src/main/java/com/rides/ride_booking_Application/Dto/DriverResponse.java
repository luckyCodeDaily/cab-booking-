package com.rides.ride_booking_Application.Dto;

import com.rides.ride_booking_Application.models.User;
import com.rides.ride_booking_Application.models.Vehicle;
import com.rides.ride_booking_Application.roles.DriverStatus;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class DriverResponse {

    private Long id;

    private Long userID;
    private DriverStatus status = DriverStatus.AVAILABLE;
    private Double currentLatitude;
    private Double currentLongitude;
    private VehicleDto vehicleDto;

    private LocalDateTime createdAT;
    private LocalDateTime updatedAT;
}

