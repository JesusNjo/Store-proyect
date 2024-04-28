package com.Store.Store.backend.entity;

import com.Store.Store.backend.entity.emuns.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Product {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotBlank
    private String description;
    @NotNull
    private Category category;

    private String image;

}
