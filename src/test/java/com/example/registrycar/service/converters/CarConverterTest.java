package com.example.registrycar.service.converters;

import com.example.registrycar.domain.Brand;
import com.example.registrycar.domain.Car;
import com.example.registrycar.entity.BrandEntity;
import com.example.registrycar.entity.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class CarConverterTest {

    @InjectMocks
    private CarConverter carConverter;

    @Mock
    private BrandConverter brandConverter;

    @Test
    void toCar_test(){
        // Given
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Toyota");
        CarEntity entity = new CarEntity();
        entity.setId(1);
        entity.setYear(2000);
        entity.setMileage(5000);
        entity.setModel("Arius");
        entity.setColour("Blue");
        entity.setBrand(brandEntity);
        entity.setDescription("Lovely car");

        Brand brand = new Brand();
        brand.setName("Toyota");

        Car car = new Car();
        car.setId(1);
        car.setYear(2000);
        car.setMileage(5000);
        car.setModel("Arius");
        car.setColour("Blue");
        car.setBrand(brand);
        car.setDescription("Lovely car");

        // When
        when(brandConverter.toBrand(brandEntity)).thenReturn(brand);

        // Then
        Car result = carConverter.toCar(entity);
        assertEquals(result.getId(), car.getId());
        assertEquals(result.getYear(), car.getYear());
        assertEquals(result.getModel(), car.getModel());
        assertEquals(result.getMileage(), car.getMileage());
        assertEquals(result.getModel(), car.getModel());
        assertEquals(result.getColour(), car.getColour());
        assertEquals(result.getBrand(), car.getBrand());

    }

    @Test
    void toEntity_test() {
        // Given
        Brand brand = new Brand();
        brand.setName("Toyota");
        Car car = new Car();
        car.setId(1);
        car.setYear(2000);
        car.setMileage(5000);
        car.setModel("Arius");
        car.setColour("Blue");
        car.setBrand(brand);
        car.setDescription("Lovely car");

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Toyota");

        CarEntity expectedEntity = new CarEntity();
        expectedEntity.setId(1);
        expectedEntity.setYear(2000);
        expectedEntity.setMileage(5000);
        expectedEntity.setModel("Arius");
        expectedEntity.setColour("Blue");
        expectedEntity.setBrand(brandEntity);
        expectedEntity.setDescription("Lovely car");

        // When
        when(brandConverter.toEntity(brand)).thenReturn(brandEntity);

        // Then
        CarEntity result = carConverter.toEntity(car);
        assertEquals(expectedEntity.getId(), result.getId());
        assertEquals(expectedEntity.getYear(), result.getYear());
        assertEquals(expectedEntity.getModel(), result.getModel());
        assertEquals(expectedEntity.getMileage(), result.getMileage());
        assertEquals(expectedEntity.getColour(), result.getColour());
        assertEquals(expectedEntity.getDescription(), result.getDescription());
        assertEquals(expectedEntity.getBrand(), result.getBrand());
    }

}
