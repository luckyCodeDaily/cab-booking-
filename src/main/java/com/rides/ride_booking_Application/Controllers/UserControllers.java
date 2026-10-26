package com.rides.ride_booking_Application.Controllers;

import com.rides.ride_booking_Application.Dto.UserRequest;
import com.rides.ride_booking_Application.Dto.UserResponse;
import com.rides.ride_booking_Application.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api-users")
@RestController
public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<>( userService.GetAll(), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<?> saveUsers(@RequestBody UserRequest userRequest){
        userService.save(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public boolean getById(@PathVariable Long id){
        return  userService.fetchById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long id){
       boolean deleted = userService.deleteById(id);
    return deleted ? new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<  UserResponse> UpdateByUser(@PathVariable Long id , @RequestBody UserRequest userRequest ){
        boolean update = userService.updateById(id, userRequest);
        if (update){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

