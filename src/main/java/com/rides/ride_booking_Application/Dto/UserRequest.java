package com.rides.ride_booking_Application.Dto;

import com.rides.ride_booking_Application.roles.UserRolls;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long phone ;
    private UserRolls rolls ;
}
