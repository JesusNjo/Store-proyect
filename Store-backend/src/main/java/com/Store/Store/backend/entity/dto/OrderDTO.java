package com.Store.Store.backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {

    private String client_id;
    private String product_id;
    private Double quantity;
    private Double totalAmount;
}
