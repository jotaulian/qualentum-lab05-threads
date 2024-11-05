package com.example.registrycar.controller.mapper;

import com.example.registrycar.controller.dtos.BrandRequest;
import com.example.registrycar.controller.dtos.BrandResponse;
import com.example.registrycar.domain.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandResponse toBrandResponse(Brand brand){
        BrandResponse brandResponse = new BrandResponse();

        brandResponse.setId(brand.getId());
        brandResponse.setName(brand.getName());
        brandResponse.setWarranty(brand.getWarranty());
        brandResponse.setCountry(brand.getCountry());

        return brandResponse;
    }

    public Brand toModel(BrandRequest model){
        Brand brand = new Brand();

        brand.setId(model.getId());
        brand.setName(model.getName());
        brand.setWarranty(model.getWarranty());
        brand.setCountry(model.getCountry());

        return brand;
    }
}
