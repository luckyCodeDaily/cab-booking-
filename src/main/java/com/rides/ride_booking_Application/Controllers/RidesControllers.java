package com.rides.ride_booking_Application.Controllers;

import com.rides.ride_booking_Application.Dto.RideRequested;
import com.rides.ride_booking_Application.Dto.RideResponse;
import com.rides.ride_booking_Application.Service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-ride")
@RequiredArgsConstructor
public class RidesControllers {

    private final RideService rideService;
    @PostMapping
    public ResponseEntity<?>BookRides(@RequestBody RideRequested ride){
        rideService.BookRides(ride);
        return ResponseEntity.ok("Ride booked Successfully");
    }
@GetMapping
    public ResponseEntity<List<RideResponse>>AllRIDES(){
        return new ResponseEntity<>(rideService.allRides(), HttpStatus.OK);
}
@PutMapping("/{id}/start")
    public ResponseEntity<RideResponse>StartRide(@PathVariable Long id){
        rideService.RideStart(id);
        return new ResponseEntity<>(HttpStatus.OK);
}
@PutMapping("/{id}/completed")
  public ResponseEntity<RideResponse>CompletedRide(@PathVariable Long id){
        rideService.CompleteRide(id);
        return new ResponseEntity<>(HttpStatus.OK);
}
}
