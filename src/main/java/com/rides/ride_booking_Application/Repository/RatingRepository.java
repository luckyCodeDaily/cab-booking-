package com.rides.ride_booking_Application.Repository;

import com.rides.ride_booking_Application.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    boolean existsByRidesId(Long rideId);
}


