package com.rides.ride_booking_Application.Repository;

import com.rides.ride_booking_Application.Dto.DriversRequest;
import com.rides.ride_booking_Application.models.Drivers;
import com.rides.ride_booking_Application.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DriverRepository extends JpaRepository<Drivers,Long> {


    Optional<Drivers>findByVehicle_VehicleNumber(String vehicleNumber);
}
