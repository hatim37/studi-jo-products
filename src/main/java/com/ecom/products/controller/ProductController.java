package com.ecom.products.controller;

import com.ecom.products.dto.ProductDto;
import com.ecom.products.entity.Product;
import com.ecom.products.repository.ProductRepository;
import com.ecom.products.services.customer.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class
ProductController {

    private final ProductService customerProductservice;
    private final ProductRepository productRepository;

    public ProductController(ProductService customerProductservice, ProductService productService, ProductRepository productRepository) {
        this.customerProductservice = customerProductservice;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(customerProductservice.getAllProducts());
    }

}
