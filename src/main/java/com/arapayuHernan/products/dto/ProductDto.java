package com.arapayuHernan.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private int stock;

    private double price;
}
