package com.rides.ride_booking_Application.Dto;

import lombok.Data;

@Data
public class RideRequested {
    private Long customerId;
    private String pickupLocation;
    private String dropLocation;
    private Double distanceKm;
    private Long duration;
}
