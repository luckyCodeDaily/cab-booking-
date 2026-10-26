package com.rides.ride_booking_Application.Controllers;

import com.rides.ride_booking_Application.Dto.PaymentRequest;
import com.rides.ride_booking_Application.Dto.PaymentResponse;
import com.rides.ride_booking_Application.Repository.PaymentRepository;
import com.rides.ride_booking_Application.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
@RequiredArgsConstructor
public class PaymentControllers {

    private final PaymentService paymentService;
    @GetMapping("/{id}")
    public ResponseEntity< PaymentResponse> getPayment(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.getByRide(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PaymentResponse> CreatePayment(@RequestBody PaymentRequest payment){
        paymentService.makePayment(payment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/confirmCash/{paymentId}")
    public ResponseEntity<PaymentResponse>ConfirmCash(@PathVariable Long paymentId,@RequestParam Long driverId){
        PaymentResponse cash = paymentService.ConfirmCash(paymentId, driverId);
            return new ResponseEntity<>(cash,HttpStatus.OK);

    }

}
