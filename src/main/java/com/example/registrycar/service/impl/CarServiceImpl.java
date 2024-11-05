package com.example.registrycar.service.impl;

import com.example.registrycar.domain.Car;
import com.example.registrycar.entity.CarEntity;
import com.example.registrycar.repository.CarRepository;
import com.example.registrycar.service.CarService;
import com.example.registrycar.service.converters.CarConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    @Override
    @Async
    public CompletableFuture<List<Car>> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll();
        List<Car> cars = new ArrayList<>();
        carEntities.forEach(carEntity -> {
            cars.add(carConverter.toCar(carEntity));
        });

        return CompletableFuture.completedFuture(cars);
    }

    @Override
    public Car getCarById(Integer id) {
        Optional<CarEntity> carOptional = carRepository.findById(id);

        return carOptional.map(carConverter::toCar).orElse(null);

    }

    @Override
    public Car saveCar(Car car) {
        CarEntity carEntity =  carConverter.toEntity(car);

        return carConverter.toCar(carEntity);
    }

    @Override
    public Car updateCar(Integer id, Car car) {
        Optional<CarEntity> carOptional = carRepository.findById(id);

        if(carOptional.isPresent()){
            CarEntity carEntity = carConverter.toEntity(car);
            carEntity.setId(id);

            return carConverter.toCar(carRepository.save(carEntity));
        }

        return null;
    }

    @Override
    public boolean deleteCar(Integer id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
