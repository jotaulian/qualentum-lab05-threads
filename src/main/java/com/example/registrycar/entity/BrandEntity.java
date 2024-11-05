package com.example.registrycar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="brand")
public class BrandEntity {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int warranty;

    private String country;

    @OneToMany(mappedBy = "brand")
    List<CarEntity> car;
}
