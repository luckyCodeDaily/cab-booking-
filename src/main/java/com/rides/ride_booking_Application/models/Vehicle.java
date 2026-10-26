package com.rides.ride_booking_Application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "vehicle_table")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String vehicleNumber;
    private String vehicleType;
    private Integer model;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Drivers driver;
}
