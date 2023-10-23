package com.lcwd.user.service.UseService.service;

import com.lcwd.user.service.UseService.entities.Hotel;
import com.lcwd.user.service.UseService.entities.Rating;
import com.lcwd.user.service.UseService.entities.User;
import com.lcwd.user.service.UseService.exception.ResourceNotFoundException;
import com.lcwd.user.service.UseService.externalServices.HotelService;
import com.lcwd.user.service.UseService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

       User user1=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        Rating[] allRatings = restTemplate.getForObject("http://Rating-Service/ratings/users/" + user1.getUserId(), Rating[].class);
        List<Rating> ratingsOfUser = Arrays.stream(allRatings).toList();
        List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://Hotel-Service/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel body = hotelService.getHotelByHotelId(rating.getHotelId());
            rating.setHotel(body);

            return rating;
        }).collect(Collectors.toList());
        user1.setRatings(ratingList);
        return user1;
    }
}
