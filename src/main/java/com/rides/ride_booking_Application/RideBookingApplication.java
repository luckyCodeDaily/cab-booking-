package com.rides.ride_booking_Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RideBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideBookingApplication.class, args);
	}

}
