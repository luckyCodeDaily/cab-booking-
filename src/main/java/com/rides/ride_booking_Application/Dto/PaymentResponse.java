package com.rides.ride_booking_Application.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private  Long rideId;
    private Long paymentId;
    private String method;
    private String status;
    private BigDecimal amount;
    private String transactionId;
    private String username;
    private String driverName;
    private LocalDateTime paidAt;
}

