package com.rides.ride_booking_Application.Dto;

import com.rides.ride_booking_Application.models.User;
import com.rides.ride_booking_Application.models.Vehicle;
import com.rides.ride_booking_Application.roles.DriverStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class DriversRequest {


        @NotNull
        private Long userId;
        @NotNull
        private Double currentLatitude;
        @NotNull
        private Double currentLongitude;

        private DriverStatus status ;
        @NonNull
        private VehicleDto vehicle;

}
