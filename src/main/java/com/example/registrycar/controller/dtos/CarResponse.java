package com.example.registrycar.controller.dtos;


import lombok.Data;

@Data
public class CarResponse {

    private Integer id;

    private BrandResponse brand;

    private String model;

    private Integer mileage;

    private Double price;

    private Integer year;

    private String description;

    private String colour;
}
