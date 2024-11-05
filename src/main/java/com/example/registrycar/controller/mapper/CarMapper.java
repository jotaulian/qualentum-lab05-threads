package com.example.registrycar.controller.mapper;

import com.example.registrycar.controller.dtos.CarRequest;
import com.example.registrycar.controller.dtos.CarResponse;
import com.example.registrycar.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    @Autowired
    private BrandMapper brandMapper;

    public CarResponse toResponse(Car car){
        CarResponse carResponse = new CarResponse();

        carResponse.setId(car.getId());
        carResponse.setBrand(brandMapper.toBrandResponse(car.getBrand()));
        carResponse.setModel(car.getModel());
        carResponse.setYear(car.getYear());
        carResponse.setColour(car.getColour());
        carResponse.setDescription(car.getDescription());
        carResponse.setMileage(car.getMileage());

        return carResponse;
    }


    public Car toModel(CarRequest model){
        Car car = new Car();

        car.setId(model.getId());
        //
        car.setBrand(brandMapper.toModel(model.getBrand()));
        car.setModel(model.getModel());
        car.setYear(model.getYear());
        car.setColour(model.getColour());
        car.setDescription(model.getDescription());
        car.setMileage(model.getMileage());

        return car;
    }

}
