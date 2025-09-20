package com.ecom.products.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private Long price;
    private String description;
    private byte[] byteImg;
    @JsonIgnore
    private MultipartFile img;
    private Long quantity;
}
