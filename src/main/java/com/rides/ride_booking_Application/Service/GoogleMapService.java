package com.rides.ride_booking_Application.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class GoogleMapService {
private static final String Url = "https://routes.googleapis.com/directions/v2:computeRoutes";

private final  static RestClient restClient= RestClient.create();
    @Value("${google.maps.api-key}")
    private String apikey;

    public RouteInfo getRoute(String pickup,String drop){

        Map<String, Object> body = Map.of(
                "origin",Map.of("address",pickup),
                "destination",Map.of("address",drop),
                "travelMode","DRIVE"

        );
        RouteResponse response = restClient.post()
                .uri(Url)
                .header("X-Goog-Api-Key",apikey)
                .header("X-Goog-FieldMask","routes.distanceMeters,routes.duration")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(RouteResponse.class);

        if (response == null|| response.routes==null||response.routes.isEmpty()) {
            throw new RuntimeException("routes not found");

        }
                Route route = response.routes.get(0);
        double km = route.distanceMeters/1000.0;
        double second = Double.parseDouble(route.duration().replace("s",""));
        long minutes = (long) Math.ceil(second / 60.0);

        return new RouteInfo(Math.round(km*10)/10.0,minutes);



    }

public record RouteInfo(Double distance, Long duration){}
    record RouteResponse(List<Route> routes){}
    record Route(long distanceMeters, String duration){}
}
