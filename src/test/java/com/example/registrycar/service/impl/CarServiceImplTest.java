package com.example.registrycar.service.impl;

import com.example.registrycar.domain.Car;
import com.example.registrycar.entity.CarEntity;
import com.example.registrycar.repository.CarRepository;
import com.example.registrycar.service.converters.CarConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarConverter carConverter;


    @Test
    void getCarById_test(){
        //Given
        CarEntity entity = new CarEntity();
        entity.setId(1);

        Car car = new Car();
        car.setId(1);
        // When
        when(carRepository.findById(1)).thenReturn(Optional.of(entity));
        when(carConverter.toCar(entity)).thenReturn(car);

        // Then
        Car result = carService.getCarById(1);
        assertEquals(result, car);
    }

    @Test
    void getCarById_test_ko(){
        //Given

        // When
        when(carRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        Car result = carService.getCarById(1);
        assertNull(result);
    }

    @Test
    void getAllCars_test() throws Exception {
        // Given
        List<CarEntity> carEntities = new ArrayList<>();
        CarEntity carEntity1 = new CarEntity();
        carEntity1.setId(1);

        CarEntity carEntity2 = new CarEntity();
        carEntity2.setId(2);

        carEntities.add(carEntity1);
        carEntities.add(carEntity2);

        List<Car> cars = new ArrayList<>();
        Car car1 = new Car();
        car1.setId(1);

        Car car2 = new Car();
        car2.setId(2);

        cars.add(car1);
        cars.add(car2);

        // When
        when(carRepository.findAll()).thenReturn(carEntities);
        when(carConverter.toCar(carEntity1)).thenReturn(car1);
        when(carConverter.toCar(carEntity2)).thenReturn(car2);

        // Then
        CompletableFuture<List<Car>> result = carService.getAllCars();
        assertEquals(result.get(), cars);
    }

    @Test
    void saveCar_test() {
        // Given
        Car car = new Car();
        car.setId(1);

        CarEntity carEntity = new CarEntity();
        carEntity.setId(1);

        // When
        when(carConverter.toEntity(car)).thenReturn(carEntity);
        when(carConverter.toCar(carEntity)).thenReturn(car);

        // Then
        Car result = carService.saveCar(car);
        assertEquals(result, car);
    }

    @Test
    void updateCar_test() {
        // Given
        Car car = new Car();
        car.setId(1);

        CarEntity carEntity = new CarEntity();
        carEntity.setId(1);

        CarEntity updatedCarEntity = new CarEntity();
        updatedCarEntity.setId(1);

        // When
        when(carRepository.findById(1)).thenReturn(Optional.of(carEntity));
        when(carConverter.toEntity(car)).thenReturn(carEntity);
        when(carRepository.save(carEntity)).thenReturn(updatedCarEntity);
        when(carConverter.toCar(updatedCarEntity)).thenReturn(car);

        // Then
        Car result = carService.updateCar(1, car);
        assertEquals(result, car);
    }

    @Test
    void updateCar_test_ko() {
        // Given
        Car car = new Car();

        // When
        when(carRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        assertThrows(NoSuchElementException.class, () -> carService.updateCar(1, car));
    }

    @Test
    void deleteCar_test() {
        // Given
        when(carRepository.existsById(1)).thenReturn(true);

        // Then
        boolean result = carService.deleteCar(1);
        verify(carRepository).deleteById(1);
        assertTrue(result);
    }

    @Test
    void deleteCar_test_ko() {
        // Given
        when(carRepository.existsById(1)).thenReturn(false);

        // Then
        boolean result = carService.deleteCar(1);
        verify(carRepository, never()).deleteById(anyInt());
        assertFalse(result);
    }

}
