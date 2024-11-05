package com.example.registrycar.service.converters;

import com.example.registrycar.domain.Brand;
import com.example.registrycar.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public class BrandConverter {
    public Brand toBrand(BrandEntity entity){
        Brand brand = new Brand();

        brand.setId(entity.getId());
        brand.setName(entity.getName());
        brand.setWarranty(entity.getWarranty());
        brand.setCountry(entity.getCountry());

        return brand;
    }

    public BrandEntity toEntity(Brand brand){
        BrandEntity entity = new BrandEntity();

        entity.setId(brand.getId());
        entity.setName(brand.getName());
        entity.setWarranty(brand.getWarranty());
        entity.setCountry(brand.getCountry());

        return entity;
    }
}
