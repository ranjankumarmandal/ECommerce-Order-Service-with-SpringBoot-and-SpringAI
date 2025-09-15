package com.example.ecommerceorder.entity;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title;
    private Double price;
    private Rating rating;

    @Data
    public static class Rating {
        private Double rate;
        private Integer count;
    }
}

