package com.ecom.products.controller;

import com.ecom.products.dto.ProductDto;
import com.ecom.products.entity.Product;
import com.ecom.products.repository.ProductRepository;
import com.ecom.products.services.customer.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MicroServiceController {

    private final ProductService productService;

    public MicroServiceController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/_internal/productFindListById")
    public ResponseEntity<List<ProductDto>> findListById(@RequestBody List<Long> ids) {
       return productService.findListById(ids);
    }

}

