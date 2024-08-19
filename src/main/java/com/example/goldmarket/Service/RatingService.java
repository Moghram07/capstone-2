package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Product;
import com.example.goldmarket.Model.Rating;
import com.example.goldmarket.Repository.RatingRepository;
import com.example.goldmarket.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final ProductService productService;

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public void updateRating(Long id, Rating rating) {
        Rating r = ratingRepository.findRatingById(id);
        if (r == null) {
            throw new ApiException("rating not found");
        }
        r.setRating(rating.getRating());
        r.setRatingDate(rating.getRatingDate());
        r.setSeller(rating.getSeller());
        r.setCustomer(rating.getCustomer());
        r.setReview(rating.getReview());
        ratingRepository.save(r);
    }

    public void deleteRating(Long id) {
        Rating r = ratingRepository.findRatingById(id);
        if (r == null) {
            throw new ApiException("rating not found");
        }
        ratingRepository.delete(r);
    }

    public List<Rating> getRatingsBySeller(Long sellerId) {
        List<Rating> ratings = ratingRepository.findBySellerId(sellerId);
        if (ratings == null) {
            throw new ApiException("rating not found");
        }
        return ratings;
    }

    public List<Rating> getRatingsByCustomer(Long customerId) {
        List<Rating> ratings = ratingRepository.findByCustomerId(customerId);
        if (ratings == null) {
            throw new ApiException("rating not found");
        }
        return ratings;
    }

    public List<Rating> getByRating(int rating){
        List<Rating> ratings = ratingRepository.findByRating(rating);
        if (ratings == null) {
            throw new ApiException("rating not found");
        }
        return ratings;
    }

    public void rateProduct(Long productId, Rating rating) {
        Product product = productService.getById(productId);
        if(product == null) {
            throw new ApiException("Product not found");
        }
        rating.setProduct(product);
        ratingRepository.save(rating);
    }

    public List<Product> getProductsByRating(int rating) {
        List<Rating> ratings = ratingRepository.findByRating(rating);
        return ratings.stream()
                .map(Rating::getProduct)
                .distinct()
                .collect(Collectors.toList());
    }
}
