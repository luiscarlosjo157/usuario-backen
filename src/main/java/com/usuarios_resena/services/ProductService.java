package com.usuarios_resena.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios_resena.entities.Product;
import com.usuarios_resena.models.dto.ProductScoreDto;
import com.usuarios_resena.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public List<ProductScoreDto> getProductsByPopularity() {
        return productRepository.findProductsOrderByPopularity();
    }





}
