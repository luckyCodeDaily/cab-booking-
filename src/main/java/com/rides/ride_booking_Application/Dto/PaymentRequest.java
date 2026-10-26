package com.rides.ride_booking_Application.Dto;

import com.rides.ride_booking_Application.roles.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {
    private Long rideId;
    pripwpvate PaymentMethod paymentMethod;
}
