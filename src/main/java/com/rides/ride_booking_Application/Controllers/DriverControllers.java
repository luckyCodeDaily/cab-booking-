package com.rides.ride_booking_Application.Controllers;

import com.rides.ride_booking_Application.Dto.DriverResponse;
import com.rides.ride_booking_Application.Dto.DriverRideResponse;
import com.rides.ride_booking_Application.Dto.DriversRequest;
import com.rides.ride_booking_Application.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-drivers")
public class DriverControllers {
@Autowired
   private DriverService driverService;
    @PutMapping("/{driverId}/rides/{rideId}/accept")
    public ResponseEntity<DriverRideResponse> acceptDriverRide(@PathVariable Long driverId,
                                                               @PathVariable Long rideId) {
        return new ResponseEntity<>(driverService.AcceptRide(rideId, driverId), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?>CreateDriver( @RequestBody DriversRequest driversRequest){
        driverService.createDriver(driversRequest);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?>UpdateDriver(@PathVariable Long id ,@RequestBody DriversRequest driversRequest){
        DriverResponse changed = driverService.UpdateDriver(id, driversRequest);
            if (changed!=null){
                return ResponseEntity.ok("status is changed");
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    @GetMapping("/search/{vehicleNumber}")
    public ResponseEntity<DriverResponse> FindByDriverByVehicleNumber(@PathVariable String vehicleNumber){

      return new ResponseEntity<>(driverService.findByVehicleNumber(vehicleNumber),HttpStatus.OK);

    }
    @DeleteMapping("/search/{id}")
    public ResponseEntity<?>DeleteDriver(@PathVariable Long id){
         driverService.DeleteDriver(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }
@GetMapping
    public ResponseEntity<?>GetAllDrivers() {
    return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
}
    }


