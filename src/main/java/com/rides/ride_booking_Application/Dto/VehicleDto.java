package com.rides.ride_booking_Application.Dto;

import com.rides.ride_booking_Application.models.Drivers;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class VehicleDto {

        @NotNull
        private String vehicleNumber;

        @NotNull
        private String vehicleType;

        @NotNull
        private Integer model;
    }

