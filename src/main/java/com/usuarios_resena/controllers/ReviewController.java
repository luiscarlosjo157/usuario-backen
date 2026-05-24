package com.usuarios_resena.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios_resena.models.dto.ReviewRequestDto;
import com.usuarios_resena.models.dto.ReviewResponseDto;
import com.usuarios_resena.services.ReviewService;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost", "http://localhost:80"}) 
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

     @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewDto) {
        ReviewResponseDto newReview = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByProduct(@PathVariable Long productId) {
        List<ReviewResponseDto> reviews = reviewService.getReviewsForProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
