package com.rides.ride_booking_Application.Service;

import com.rides.ride_booking_Application.Dto.UserRequest;
import com.rides.ride_booking_Application.Dto.UserResponse;
import com.rides.ride_booking_Application.Repository.UserRepository;
import com.rides.ride_booking_Application.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

        @Autowired
        private  UserRepository userRepository;

        public boolean save(UserRequest userRequest){
            User users = new User();
            UpdateFromUserRequest( users,userRequest);
            userRepository.save(users);
            return true;
        }


        public List<UserResponse> GetAll(){
            return userRepository.findAll().stream().map(this::MaptoUserResponse).collect(Collectors.toList());
        }
        public boolean fetchById(Long id){
            Optional<User> byId = userRepository.findById(id);
            return byId.isPresent();

        }
        public Boolean deleteById(Long id){

            Optional<User> byId = userRepository.findById(id);
            if (byId.isPresent()){
                userRepository.delete(byId.get());
                return true;
            }
            return false;

        }
        public boolean updateById(Long id , UserRequest userRequest){
            userRepository.findById(id).map(exitingUser->{
                UpdateFromUserRequest(exitingUser,userRequest);
                userRepository.save(exitingUser);
                return true;
            });
            return false;
        }


    private void UpdateFromUserRequest(User user, UserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRolls(request.getRolls());
    }
    private UserResponse MaptoUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRolls(user.getRolls());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;

    }


}