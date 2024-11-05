package com.example.registrycar.controller.dtos;

import lombok.Data;

@Data
public class BrandResponse {
    private int id;

    private String name;

    private int warranty;

    private String country;
}
