package com.example.registrycar.service.converters;

import com.example.registrycar.domain.Brand;
import com.example.registrycar.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
 class BrandConverterTest {

    @InjectMocks
    private BrandConverter brandConverter;

    @Test
    void toBrand_test() {
        // Given
        BrandEntity entity = new BrandEntity();
        entity.setId(1);
        entity.setName("Toyota");
        entity.setWarranty(5);
        entity.setCountry("Japan");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1);
        expectedBrand.setName("Toyota");
        expectedBrand.setWarranty(5);
        expectedBrand.setCountry("Japan");

        // When
        Brand result = brandConverter.toBrand(entity);

        // Then
        assertEquals(expectedBrand.getId(), result.getId());
        assertEquals(expectedBrand.getName(), result.getName());
        assertEquals(expectedBrand.getWarranty(), result.getWarranty());
        assertEquals(expectedBrand.getCountry(), result.getCountry());
    }

    @Test
    void toEntity_test() {
        // Given
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Toyota");
        brand.setWarranty(5);
        brand.setCountry("Japan");

        BrandEntity expectedEntity = new BrandEntity();
        expectedEntity.setId(1);
        expectedEntity.setName("Toyota");
        expectedEntity.setWarranty(5);
        expectedEntity.setCountry("Japan");

        // When
        BrandEntity result = brandConverter.toEntity(brand);

        // Then
        assertEquals(expectedEntity.getId(), result.getId());
        assertEquals(expectedEntity.getName(), result.getName());
        assertEquals(expectedEntity.getWarranty(), result.getWarranty());
        assertEquals(expectedEntity.getCountry(), result.getCountry());
    }
}
