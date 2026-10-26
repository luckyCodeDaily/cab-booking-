package com.rides.ride_booking_Application.models;

import com.rides.ride_booking_Application.roles.PaymentMethod;
import com.rides.ride_booking_Application.roles.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "payment_table")
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ride_id", unique = true)
    private Rides rides;
private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod=PaymentMethod.UPI;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.Successfull;

    private String TransactionId;
@CreationTimestamp
    private LocalDateTime createdAt;

private LocalDateTime paidAt;
}
