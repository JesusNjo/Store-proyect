package com.Store.Store.backend.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Order {

    @Id
    private String id;
    @NotNull
    private String client_id;
    @NotNull
    private String product_id;
    @NotNull
    private Double quantity;
    @NotNull
    private Double totalAmount;
}
