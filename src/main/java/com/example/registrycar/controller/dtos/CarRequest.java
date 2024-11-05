package com.example.registrycar.controller.dtos;

import lombok.Data;

@Data
public class CarRequest {
    private Integer id;

    private BrandRequest brand;

    private String model;

    private Integer mileage;

    private Double price;

    private Integer year;

    private String description;

    private String colour;
}
