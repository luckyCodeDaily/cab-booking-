package com.rides.ride_booking_Application.models;

import com.rides.ride_booking_Application.roles.RidesStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "rides")
@Data
@NoArgsConstructor
public class Rides {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Drivers driver;

    private String pickupLocation;
    private String dropLocation;
    private Double fare;
private Double distanceKm;
    private Long duration;
    @Enumerated(EnumType.STRING)
private RidesStatus status;
    @CreationTimestamp
    private LocalDateTime requestedTime;
    private LocalDateTime startedTime;
    private LocalDateTime completedTime;
}
