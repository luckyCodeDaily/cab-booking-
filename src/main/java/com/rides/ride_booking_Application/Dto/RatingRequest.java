package com.rides.ride_booking_Application.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class RatingRequest {
    private Long rideId;
    private Long costumerId;
    @NotNull
    @Min(1) @Max(5) private Integer stars;
    private String comment;
}
