package com.rides.ride_booking_Application.Service;

import com.rides.ride_booking_Application.Dto.DriverResponse;
import com.rides.ride_booking_Application.Dto.DriverRideResponse;
import com.rides.ride_booking_Application.Dto.DriversRequest;
import com.rides.ride_booking_Application.Dto.VehicleDto;
import com.rides.ride_booking_Application.Repository.DriverRepository;
import com.rides.ride_booking_Application.Repository.RidesRepository;
import com.rides.ride_booking_Application.Repository.UserRepository;
import com.rides.ride_booking_Application.models.Drivers;
import com.rides.ride_booking_Application.models.Rides;
import com.rides.ride_booking_Application.models.User;
import com.rides.ride_booking_Application.models.Vehicle;
import com.rides.ride_booking_Application.roles.DriverStatus;
import com.rides.ride_booking_Application.roles.RidesStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final RidesRepository ridesRepository;

@Transactional
    public DriverRideResponse AcceptRide(Long rideId,Long driverId){
        Rides rides = ridesRepository.findById(rideId).orElseThrow(() -> new RuntimeException("ride not found"));
        Drivers drivers = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("driver not found"));
        if (RidesStatus.REQUESTED!=rides.getStatus()){
            throw new RuntimeException("Ride not found ");
        }
        if (DriverStatus.AVAILABLE!=drivers.getStatus()){
            throw new RuntimeException("driver not found");
        }
        rides.setDriver(drivers);
        rides.setStatus(RidesStatus.ACCEPTED);
        ridesRepository.save(rides);
        return DriverAndRidesResponse(drivers,rides);



    }



    public DriverResponse createDriver(DriversRequest request) {
        Drivers drivers = new Drivers();
        applyRequest(drivers, request);
        return toResponse(driverRepository.save(drivers));
    }

    public List<DriverResponse> getAllDrivers() {
        return driverRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public DriverResponse UpdateDriver(Long id, DriversRequest request) {
        Drivers drivers = findAndThrow(id);
        applyRequest(drivers, request);
        return toResponse(driverRepository.save(drivers));
    }
    public void DeleteDriver(Long id){
        driverRepository.delete(findAndThrow(id));
    }
    public DriverResponse findByVehicleNumber(String vehicleNumber){
        return driverRepository.findByVehicle_VehicleNumber(vehicleNumber).map(this::toResponse).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"NOT FOUND" + vehicleNumber));
    }

    private Drivers findAndThrow(Long id) {
    return     driverRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "do not found in there" + id));
    }





    private void applyRequest(Drivers driver, DriversRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + request.getUserId()));
        driver.setUser(user);
        driver.setCurrentLatitude(request.getCurrentLatitude());
        driver.setCurrentLongitude(request.getCurrentLongitude());
        VehicleDto dto = request.getVehicle();
        if (dto != null) {
            // reuse the existing vehicle on update instead of creating a new row
            Vehicle vehicle = driver.getVehicle() != null ? driver.getVehicle() : new Vehicle();
            vehicle.setVehicleNumber(dto.getVehicleNumber());
            vehicle.setVehicleType(dto.getVehicleType());
            vehicle.setModel(dto.getModel());
            vehicle.setDriver(driver);   // keep both sides in sync
            driver.setVehicle(vehicle);
        }
    }


    private DriverResponse toResponse(Drivers drivers) {
        DriverResponse response = new DriverResponse();
        response.setId(drivers.getId());
        response.setUserID(drivers.getUser().getId());
        response.setCurrentLongitude(drivers.getCurrentLongitude());
        response.setCurrentLatitude(drivers.getCurrentLatitude());
        response.setStatus(drivers.getStatus());
        response.setCreatedAT(drivers.getCreatedAT());
        response.setUpdatedAT(drivers.getUpdatedAT());
if (drivers.getVehicle() !=null){
    VehicleDto vehicle = new VehicleDto();
    vehicle.setVehicleNumber(drivers.getVehicle().getVehicleNumber());
    vehicle.setVehicleType(drivers.getVehicle().getVehicleType());
    vehicle.setModel(drivers.getVehicle().getModel());
 response.setVehicleDto(vehicle);
}
return response;
    }
    private DriverRideResponse DriverAndRidesResponse(Drivers drivers, Rides rides) {
        DriverRideResponse res = new DriverRideResponse();
        res.setRideId(rides.getId());
        res.setRideStatus(rides.getStatus().name());
        res.setDriverStatus(drivers.getStatus().name());
        res.setCustomerName(rides.getCustomer().getFirstName());
        res.setCustomerPhone(String.valueOf(rides.getCustomer().getPhone()));
        res.setPickupLocation(rides.getPickupLocation());
        res.setDropLocation(rides.getDropLocation());
        res.setFare(rides.getFare());
        return res;
    }
}