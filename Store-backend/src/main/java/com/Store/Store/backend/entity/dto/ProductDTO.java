package com.Store.Store.backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class ProductDTO {


    private String id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private String image;
}
