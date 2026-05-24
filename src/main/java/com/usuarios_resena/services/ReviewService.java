package com.usuarios_resena.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios_resena.entities.Product;
import com.usuarios_resena.entities.Review;
import com.usuarios_resena.entities.User;
import com.usuarios_resena.models.dto.ProductDto;
import com.usuarios_resena.models.dto.ReviewRequestDto;
import com.usuarios_resena.models.dto.ReviewResponseDto;
import com.usuarios_resena.models.dto.SentimentResponse;
import com.usuarios_resena.models.dto.UserDto;
import com.usuarios_resena.repositories.ProductRepository;
import com.usuarios_resena.repositories.ReviewRepository;
import com.usuarios_resena.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    public ReviewResponseDto createReview(ReviewRequestDto reviewDto) {
       
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + reviewDto.getUserId()));

        Product product = productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + reviewDto.getProductId()));

        
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        
        Review savedReview = reviewRepository.save(review);

        String sentimentResult = "No disponible";
        try {
            SentimentResponse sentiment = sentimentAnalysisService.analyze(savedReview.getComment());
            sentimentResult = sentiment.getSentiment();
            savedReview.setSentiment(sentimentResult);
            reviewRepository.save(savedReview);
            System.out.println("Análisis de Sentimiento para la reseña #" + savedReview.getId() + ": " + sentiment.getSentiment());

        } catch (Exception e) {
            System.err.println("Error al analizar el sentimiento: " + e.getMessage());
            savedReview.setSentiment("Error de análisis");
            reviewRepository.save(savedReview);
        }

         return convertToDto(savedReview);
    }

        private ReviewResponseDto convertToDto(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setSentiment(review.getSentiment());

    
        UserDto userDto = new UserDto();
        userDto.setId(review.getUser().getId());
        userDto.setUsername(review.getUser().getUsername());
        
        dto.setUser(userDto);
        
        ProductDto productDto = new ProductDto();
        productDto.setId(review.getProduct().getId());
        productDto.setName(review.getProduct().getName());
        
        dto.setProduct(productDto);

        return dto;
    }

    public List<ReviewResponseDto> getReviewsForProduct(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        // Usamos streams para convertir cada elemento de la lista a su DTO
        return reviews.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
}
