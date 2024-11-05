package com.example.registrycar.service.converters;

import com.example.registrycar.entity.CarEntity;
import com.example.registrycar.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {
    @Autowired
    private BrandConverter brandConverter;

    public Car toCar(CarEntity entity){
        Car car = new Car();
        car.setId(entity.getId());
        car.setBrand(brandConverter.toBrand(entity.getBrand()));
        car.setModel(entity.getModel());
        car.setYear(entity.getYear());
        car.setColour(entity.getColour());
        car.setDescription(entity.getDescription());
        car.setMileage(entity.getMileage());

        return car;
    }


    public CarEntity toEntity(Car car){
        CarEntity carEntity = new CarEntity();

        carEntity.setId(car.getId());
        carEntity.setBrand(brandConverter.toEntity(car.getBrand()));
        carEntity.setModel(car.getModel());
        carEntity.setYear(car.getYear());
        carEntity.setColour(car.getColour());
        carEntity.setDescription(car.getDescription());
        carEntity.setMileage(car.getMileage());

        return carEntity;
    }
}
