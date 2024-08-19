package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findRatingById(Long id);

    List<Rating> findBySellerId(Long sellerId);

    List<Rating> findByCustomerId(Long customerId);

    List<Rating> findByRating(int rating);


}
