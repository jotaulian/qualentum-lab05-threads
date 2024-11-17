package com.example.registrycar.controller.mappers;
import com.example.registrycar.controller.dtos.BrandRequest;
import com.example.registrycar.controller.dtos.BrandResponse;
import com.example.registrycar.controller.dtos.CarRequest;
import com.example.registrycar.controller.dtos.CarResponse;
import com.example.registrycar.controller.mapper.BrandMapper;
import com.example.registrycar.controller.mapper.CarMapper;
import com.example.registrycar.domain.Brand;
import com.example.registrycar.domain.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarMapperTest {
    @InjectMocks
    private CarMapper carMapper;

    @Mock
    private BrandMapper brandMapper;

    @Test
    void toResponse_test() {
        // Given
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Toyota");

        Car car = new Car();
        car.setId(1);
        car.setBrand(brand);
        car.setModel("Corolla");
        car.setYear(2020);
        car.setColour("Red");
        car.setDescription("Reliable car");
        car.setMileage(15000);

        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setId(1);
        brandResponse.setName("Toyota");

        // When
        when(brandMapper.toBrandResponse(brand)).thenReturn(brandResponse);

        // Then
        CarResponse carResponse = carMapper.toResponse(car);
        assertEquals(1, carResponse.getId());
        assertEquals("Corolla", carResponse.getModel());
        assertEquals(2020, carResponse.getYear());
        assertEquals("Red", carResponse.getColour());
        assertEquals("Reliable car", carResponse.getDescription());
        assertEquals(15000, carResponse.getMileage());
        assertEquals(brandResponse, carResponse.getBrand());
    }

    @Test
    void toModel_test() {
        // Given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setId(1);
        brandRequest.setName("Toyota");

        CarRequest carRequest = new CarRequest();
        carRequest.setId(1);
        carRequest.setBrand(brandRequest);
        carRequest.setModel("Corolla");
        carRequest.setYear(2020);
        carRequest.setColour("Red");
        carRequest.setDescription("Reliable car");
        carRequest.setMileage(15000);

        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Toyota");

        // When
        when(brandMapper.toModel(brandRequest)).thenReturn(brand);

        // Then
        Car car = carMapper.toModel(carRequest);
        assertEquals(1, car.getId());
        assertEquals("Corolla", car.getModel());
        assertEquals(2020, car.getYear());
        assertEquals("Red", car.getColour());
        assertEquals("Reliable car", car.getDescription());
        assertEquals(15000, car.getMileage());
        assertEquals(brand, car.getBrand());
    }
}
