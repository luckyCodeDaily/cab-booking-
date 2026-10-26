package com.rides.ride_booking_Application.Dto;

import lombok.Data;

@Data
public class DriverRideResponse {
    private Long rideId;
    private String rideStatus;
    private String driverStatus;

    private String customerName;
    private String customerPhone;
    private String pickupLocation;
    private String dropLocation;
    private Double fare;
}
