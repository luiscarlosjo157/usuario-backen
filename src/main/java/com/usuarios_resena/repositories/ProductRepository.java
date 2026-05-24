package com.usuarios_resena.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usuarios_resena.entities.Product;
import com.usuarios_resena.models.dto.ProductScoreDto;

public interface ProductRepository extends JpaRepository<Product, Long> {
     @Query("SELECT new com.usuarios_resena.models.dto.ProductScoreDto(" +
           "p, " +
           "COALESCE(AVG(r.rating), 0.0), " + 
           "COUNT(r)) " +
           "FROM Product p LEFT JOIN Review r ON p.id = r.product.id " +
           "GROUP BY p.id " +
           "ORDER BY AVG(r.rating) DESC NULLS LAST, COUNT(r) DESC")
    List<ProductScoreDto> findProductsOrderByPopularity();
}
