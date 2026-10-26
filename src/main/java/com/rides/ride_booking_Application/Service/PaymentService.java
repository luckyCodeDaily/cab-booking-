package com.rides.ride_booking_Application.Service;

import com.rides.ride_booking_Application.Dto.PaymentRequest;
import com.rides.ride_booking_Application.Dto.PaymentResponse;
import com.rides.ride_booking_Application.Repository.PaymentRepository;
import com.rides.ride_booking_Application.Repository.RidesRepository;
import com.rides.ride_booking_Application.models.Payment;
import com.rides.ride_booking_Application.models.Rides;
import com.rides.ride_booking_Application.roles.PaymentMethod;
import com.rides.ride_booking_Application.roles.PaymentStatus;
import com.rides.ride_booking_Application.roles.RidesStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.IClassFileProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RidesRepository ridesRepository;
    // make payment
    public PaymentResponse makePayment(PaymentRequest request){
        Rides rides = ridesRepository.findById(request.getRideId()).orElseThrow(() -> new RuntimeException("ride d nahi mila "));
if (rides.getStatus() != RidesStatus.COMPLETED){
    throw new RuntimeException("ride is not completed");
}
if (paymentRepository.existsByRidesId(rides.getId())) {
    throw new RuntimeException("is ride ki payment ho gai hai");

}
            Payment payment = new Payment();
            payment.setRides(rides);
            payment.setAmount(BigDecimal.valueOf(rides.getFare()));
 payment.setPaymentMethod(request.getPaymentMethod());
if (request.getPaymentMethod()== PaymentMethod.CASH){
    payment.setPaymentStatus(PaymentStatus.Pending);
}
else {
    payment.setPaymentStatus(PaymentStatus.Successfull);
    payment.setTransactionId("TXN" + UUID.randomUUID());
    payment.setPaidAt(LocalDateTime.now());
}
return mapToResponse(paymentRepository.save(payment));
    }
public PaymentResponse ConfirmCash(Long paymentId,Long driverId){
    Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment nahi mila"));
    if (payment.getPaymentMethod()== PaymentMethod.CASH){
        payment.setPaymentStatus(PaymentStatus.Pending);
    }
    if (!payment.getRides().getDriver().getId().equals(driverId)){
        throw new RuntimeException("this ride is not yours ");
    }
    payment.setPaymentStatus(PaymentStatus.Successfull);
    payment.setPaidAt(LocalDateTime.now());
    return mapToResponse(paymentRepository.save(payment));

}
    public PaymentResponse getByRide(Long rideId) {
        return  paymentRepository.findByRidesId(rideId).map(this::mapToResponse).orElseThrow(()->new RuntimeException("ride not found "));
    }

    private PaymentResponse mapToResponse(Payment pay) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(pay.getId());
        response.setRideId(pay.getRides().getId());
        response.setAmount(pay.getAmount());
        response.setMethod(String.valueOf(pay.getPaymentMethod()));
        response.setStatus(String.valueOf(pay.getPaymentStatus()));
        response.setTransactionId(pay.getTransactionId());
        response.setPaidAt(pay.getPaidAt());
        response.setUsername(pay.getRides().getCustomer().getFirstName());

        if (pay.getRides().getDriver() != null) {
            response.setDriverName(pay.getRides().getDriver().getUser().getFirstName());
        }
        return response;
    }
}
