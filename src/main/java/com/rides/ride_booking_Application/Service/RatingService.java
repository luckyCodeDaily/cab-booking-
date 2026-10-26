package com.rides.ride_booking_Application.Service;

import com.rides.ride_booking_Application.Dto.RatingRequest;
import com.rides.ride_booking_Application.Dto.RatingResponse;
import com.rides.ride_booking_Application.Repository.DriverRepository;
import com.rides.ride_booking_Application.Repository.RatingRepository;
import com.rides.ride_booking_Application.Repository.RidesRepository;
import com.rides.ride_booking_Application.models.Drivers;
import com.rides.ride_booking_Application.models.Rating;
import com.rides.ride_booking_Application.models.Rides;
import com.rides.ride_booking_Application.roles.RidesStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RidesRepository ridesRepository;
    private final DriverRepository driverRepository;
    @Transactional
    public RatingResponse Rate(RatingRequest request) {
        Rides rides =ridesRepository.findById(request.getRideId()).orElseThrow(()->new RuntimeException("Ride not found"));
        if (rides.getStatus()!=RidesStatus.COMPLETED){
            throw new RuntimeException("ride does not completed");
        }
        if (!rides.getCustomer().getId().equals(request.getCostumerId())){
            throw new RuntimeException("Customer not found");

        }
        if (ratingRepository.existsByRidesId(request.getRideId())){
            throw new RuntimeException("rating already exist");
        }
Drivers drivers = rides.getDriver();

        Rating rating = new Rating();
        rating.setRides(rides);
        rating.setDriver(drivers);
        rating.setStar(request.getStars());
        rating.setComment(request.getComment());
        ratingRepository.save(rating);

        int count = drivers.getRatingCount();
        double avg= (drivers.getAverageRating()*count+request.getStars()) / (count+1);
        drivers.setAverageRating(Math.round(avg*10.0)/10.0);
        drivers.setRatingCount(count+1);
        driverRepository.save(drivers);
        RatingResponse response = new RatingResponse();
        response.setRatingId(rating.getId());
        response.setRating(Double.valueOf(drivers.getRatingCount()));
        response.setDriverAverageRating(drivers.getAverageRating());
        response.setStars(rating.getStar());
        response.setComment(rating.getComment());
        response.setDriverName(rides.getDriver().getUser().getFirstName());
        return response;
    }
}
