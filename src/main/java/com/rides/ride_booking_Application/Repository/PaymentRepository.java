package com.rides.ride_booking_Application.Repository;
import com.rides.ride_booking_Application.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRidesId(Long rideId);
    boolean existsByRidesId(Long rideId);

}
