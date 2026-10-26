package com.rides.ride_booking_Application.models;

import com.rides.ride_booking_Application.roles.DriverStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Driver_table")
@Getter
@Setter
@NoArgsConstructor
public class Drivers {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @OneToOne
        @JoinColumn(name = "user_id", nullable = false,unique = true)
        private User user;

        private Double currentLatitude;
        private Double currentLongitude;
        private Double averageRating= 0.0;
        private Integer ratingCount= 0;
        @Enumerated(EnumType.STRING)
        private DriverStatus status = DriverStatus.AVAILABLE;
        @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
        private Vehicle vehicle;

        @CreationTimestamp
        private LocalDateTime createdAT;
        @UpdateTimestamp
        private LocalDateTime updatedAT;


        }
