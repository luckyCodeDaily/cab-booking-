package com.rides.ride_booking_Application.Dto;

import lombok.Data;

@Data
public class RatingResponse {
    private Long ratingId;
    private Double rating;
    private String comment;
    private String driverName;
    private Double driverAverageRating;
    private Integer stars;
}
