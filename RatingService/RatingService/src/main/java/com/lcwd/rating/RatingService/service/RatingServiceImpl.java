package com.lcwd.rating.RatingService.service;

import com.lcwd.rating.RatingService.entities.Rating;
import com.lcwd.rating.RatingService.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository repository;

    @Override
    public Rating create(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        return repository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
