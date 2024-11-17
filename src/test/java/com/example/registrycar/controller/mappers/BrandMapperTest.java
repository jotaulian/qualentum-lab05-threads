package com.example.registrycar.controller.mappers;
import com.example.registrycar.controller.dtos.BrandRequest;
import com.example.registrycar.controller.dtos.BrandResponse;
import com.example.registrycar.controller.mapper.BrandMapper;
import com.example.registrycar.domain.Brand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class BrandMapperTest {
    private final BrandMapper brandMapper = new BrandMapper();

    @Test
    void toBrandResponse_test() {
        // Given
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Toyota");
        brand.setWarranty(5);
        brand.setCountry("Japan");

        // When
        BrandResponse brandResponse = brandMapper.toBrandResponse(brand);

        // Then
        assertEquals(1, brandResponse.getId());
        assertEquals("Toyota", brandResponse.getName());
        assertEquals(5, brandResponse.getWarranty());
        assertEquals("Japan", brandResponse.getCountry());
    }

    @Test
    void toModel_test() {
        // Given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setId(1);
        brandRequest.setName("Toyota");
        brandRequest.setWarranty(5);
        brandRequest.setCountry("Japan");

        // When
        Brand brand = brandMapper.toModel(brandRequest);

        // Then
        assertEquals(1, brand.getId());
        assertEquals("Toyota", brand.getName());
        assertEquals(5, brand.getWarranty());
        assertEquals("Japan", brand.getCountry());
    }
}
