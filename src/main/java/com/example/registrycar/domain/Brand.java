package com.example.registrycar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private int id;

    private String name;

    private int warranty;

    private String country;

}
