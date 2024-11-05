package com.example.registrycar.controller.dtos;

import lombok.Data;

@Data
public class BrandRequest {
    private int id;

    private String name;

    private int warranty;

    private String country;
}
