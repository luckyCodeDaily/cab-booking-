package com.rides.ride_booking_Application.Service;

import com.rides.ride_booking_Application.Dto.RideRequested;
import com.rides.ride_booking_Application.Dto.RideResponse;
import com.rides.ride_booking_Application.Repository.DriverRepository;
import com.rides.ride_booking_Application.Repository.RidesRepository;
import com.rides.ride_booking_Application.Repository.UserRepository;
import com.rides.ride_booking_Application.models.Drivers;
import com.rides.ride_booking_Application.models.Rides;
import com.rides.ride_booking_Application.models.User;
import com.rides.ride_booking_Application.roles.DriverStatus;
import com.rides.ride_booking_Application.roles.RidesStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RideService {
    private final RidesRepository ridesRepository;
    private  final UserRepository userRepository;
    private  final DriverRepository driverRepository;
    private final GoogleMapService googleMapService;

    public RideResponse BookRides(RideRequested requested){
        User customer = userRepository.findById(requested.getCustomerId()).orElseThrow(() ->
                new RuntimeException("user not found"));
        GoogleMapService.RouteInfo route = googleMapService.getRoute(requested.getPickupLocation(), requested.getDropLocation());
        Rides rides = new Rides();
rides.setCustomer(customer);
rides.setPickupLocation(requested.getPickupLocation());
rides.setDropLocation(requested.getDropLocation());
rides.setDistanceKm(route.distance());
rides.setDuration(route.duration());
rides.setFare(50+route.distance()*12);
rides.setStatus(RidesStatus.REQUESTED);
return MapToResponse(ridesRepository.save(rides));
    }
    public List<RideResponse>allRides(){
        return ridesRepository.findAll().stream().map(this::MapToResponse).collect(Collectors.toList());
    }
 public RideResponse RideStart(Long rideId){
     Rides rides = ridesRepository.findById(rideId).orElseThrow(()->new RuntimeException("ride nahi mili "));
     if (rides.getStatus()!=RidesStatus.ACCEPTED)
         throw new RuntimeException("Pehle driver ride accept kare ");
     rides.setStatus(RidesStatus.STARTED);
     rides.setStartedTime(LocalDateTime.now());
     return MapToResponse(ridesRepository.save(rides));
 }
public RideResponse CompleteRide(Long rideId){
    Rides rides = ridesRepository.findById(rideId).orElseThrow(() -> new RuntimeException("ride mahi mili"));
if (rides.getStatus()!=RidesStatus.STARTED)
    throw new RuntimeException("ride cannot started");
rides.setStatus(RidesStatus.COMPLETED);
    Drivers drivers = rides.getDriver();
    drivers.setStatus(DriverStatus.AVAILABLE);
    rides.setCompletedTime(LocalDateTime.now());
    driverRepository.save(drivers);

return MapToResponse(ridesRepository.save(rides));
}

    private RideResponse MapToResponse(Rides ride) {
        RideResponse response = new RideResponse();
        response.setRideId(ride.getId());
        response.setPickupLocation(ride.getPickupLocation());
        response.setDropLocation(ride.getDropLocation());
        response.setFare(ride.getFare());
        response.setStatus(ride.getStatus());
        response.setDistanceKm(ride.getDistanceKm());
response.setDuration(ride.getDuration());
        response.setCustomerName(ride.getCustomer().getFirstName() + " "+ ride.getCustomer().getLastName());

        if (ride.getDriver() !=null){
            User driveUser = ride.getDriver().getUser();
            response.setDriverName(driveUser.getFirstName() + " "+ driveUser.getLastName());
            response.setDriverPhone(String.valueOf(driveUser.getPhone()));
            if (ride.getDriver().getVehicle()!=null){
                response.setVehicleNumber(ride.getDriver().getVehicle().getVehicleNumber());
            }
        }
return  response;
    }


}
