package com.rides.ride_booking_Application.Repository;

import com.rides.ride_booking_Application.models.Rides;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RidesRepository extends JpaRepository<Rides,Long> {

    

}
