package com.usuarios_resena.models.dto;

import com.usuarios_resena.entities.Product;

public class ProductScoreDto {
    private Product product;
    private Double averageRating;
    private Long reviewCount;

    public ProductScoreDto(Product product, Double averageRating, Long reviewCount) {
        this.product = product;
        this.averageRating = averageRating != null ? averageRating : 0.0;
        this.reviewCount = reviewCount != null ? reviewCount : 0L;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    

}
