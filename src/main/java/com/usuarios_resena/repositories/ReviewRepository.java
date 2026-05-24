package com.usuarios_resena.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarios_resena.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);

}
