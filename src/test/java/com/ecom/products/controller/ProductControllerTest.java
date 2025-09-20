package com.ecom.products.controller;


import com.ecom.products.dto.ProductDto;
import com.ecom.products.services.customer.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProducts() throws Exception {
        byte[] image = new byte[]{1, 2, 3};

        //On créer 2 produits
        ProductDto dto1 = new ProductDto();
        dto1.setId(1L);
        dto1.setName("Solo");
        dto1.setDescription("1 personne");
        dto1.setPrice(50L);
        dto1.setQuantity(1L);
        dto1.setByteImg(image);

        ProductDto dto2 = new ProductDto();
        dto2.setId(2L);
        dto2.setName("Duo");
        dto2.setDescription("2 personnes");
        dto2.setPrice(80L);
        dto2.setQuantity(1L);
        dto2.setByteImg(image);

        List<ProductDto> products = Arrays.asList(dto1, dto2);

        //vVérifie que le service retourne bien la liste des produits
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        //On vérifie le endpoint du controller avec le retour des produits créés
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(products.size()))
                .andExpect(jsonPath("$[0].name").value("Solo"))
                .andExpect(jsonPath("$[1].price").value(80));
    }
}