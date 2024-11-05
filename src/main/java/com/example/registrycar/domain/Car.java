package com.example.registrycar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Integer id;

    private Brand brand;

    private String model;

    private Integer mileage;

    private Double price;

    private Integer year;

    private String description;

    private String colour;

}
