package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Rating;
import com.example.goldmarket.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/get")
    public ResponseEntity getRatings() {
        List<Rating> ratings = ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/add")
    public ResponseEntity addRating(@RequestBody @Valid Rating rating, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ratingService.addRating(rating);
        return ResponseEntity.status(200).body("Rating added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateRating(@PathVariable long id, @RequestBody @Valid Rating rating, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ratingService.updateRating(id, rating);
        return ResponseEntity.status(200).body("Rating updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRating(@PathVariable long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(200).body("Rating deleted");
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity getRatingsBySeller(@PathVariable long sellerId) {
        List<Rating> ratings = ratingService.getRatingsBySeller(sellerId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity getRatingsByCustomer(@PathVariable long customerId) {
        List<Rating> ratings = ratingService.getRatingsByCustomer(customerId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity getByRating(@PathVariable int rating) {
        List<Rating> ratings = ratingService.getByRating(rating);
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/rateProduct/{productId}")
    public ResponseEntity rateProduct(@PathVariable Long productId, @RequestBody @Valid Rating rating, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ratingService.rateProduct(productId, rating);
        return ResponseEntity.status(200).body("Product rated");
    }

    @GetMapping("/products/{rating}")
    public ResponseEntity getProductsByRating(@PathVariable int rating) {
        return ResponseEntity.ok(ratingService.getProductsByRating(rating));
    }
}
