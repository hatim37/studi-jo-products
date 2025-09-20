package com.ecom.products.services;

import com.ecom.products.dto.ProductDto;
import com.ecom.products.entity.Product;
import com.ecom.products.repository.ProductRepository;
import com.ecom.products.services.customer.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        byte[] image = new byte[]{1, 2, 3};

        Product p1 = Product.builder()
                .id(1L)
                .name("Solo")
                .description("1 personne")
                .price(50L)
                .quantity(1L)
                .img(image)
                .build();

        Product p2 = Product.builder()
                .id(2L)
                .name("Duo")
                .description("2 personnes")
                .price(80L)
                .quantity(1L)
                .img(image)
                .build();

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<ProductDto> result = productService.getAllProducts();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Solo", result.get(0).getName());
        Assertions.assertEquals(80L, result.get(1).getPrice());
        Assertions.assertArrayEquals(image, result.get(0).getByteImg());
    }
}
