package com.usuarios_resena.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios_resena.entities.Product;
import com.usuarios_resena.models.dto.ProductScoreDto;
import com.usuarios_resena.services.ProductService;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost", "http://localhost:80"}) 
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(
        @RequestParam(required = false, defaultValue = "default") String sortBy){
        
        if ("popularity".equals(sortBy)) {
           List<ProductScoreDto> products = productService.getProductsByPopularity();
            return new ResponseEntity<>(products, HttpStatus.OK);
            }
        
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
