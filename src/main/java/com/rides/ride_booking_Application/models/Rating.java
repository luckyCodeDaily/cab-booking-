package com.rides.ride_booking_Application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "rate")
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @OneToOne
    @JoinColumn(name = "ride_id",unique = true)
    private Rides rides;
    @ManyToOne(optional = false)
    @JoinColumn(name = "driver_id")
    private Drivers driver;

    private Integer star;
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
