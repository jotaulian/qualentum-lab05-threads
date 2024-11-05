package com.example.registrycar.service;

import com.example.registrycar.domain.Car;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    CompletableFuture<List<Car>> getAllCars();

    Car getCarById(Integer id);

    Car saveCar(Car car);

    Car updateCar(Integer id, Car car);

    boolean deleteCar(Integer id);
}