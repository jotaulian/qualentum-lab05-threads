package com.example.registrycar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car")
public class CarEntity {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="brand_id", nullable = false)
    private BrandEntity brand;

    private String model;

    private int mileage;

    private double price;

    private int year;

    private String colour;

    private String description;

    private String fuelType;

    private int numDoors;
}
