package com.rides.ride_booking_Application.Controllers;

import com.rides.ride_booking_Application.Dto.RatingRequest;
import com.rides.ride_booking_Application.Dto.RatingResponse;
import com.rides.ride_booking_Application.Service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-rate")
public class RatingController {
    private final RatingService service;
    @PostMapping
    public ResponseEntity<RatingRequest>AddRating(@RequestBody RatingRequest request){
        RatingResponse rate = service.Rate(request);
        if (rate != null){
            return ResponseEntity.ok().body(request);
        }
return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
