package com.example.registrycar.controller;

import com.example.registrycar.controller.dtos.CarRequest;
import com.example.registrycar.controller.dtos.CarResponse;
import com.example.registrycar.controller.mapper.CarMapper;
import com.example.registrycar.domain.Car;
import com.example.registrycar.repository.CarRepository;
import com.example.registrycar.service.CarService;
import com.example.registrycar.service.converters.CarConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENT', 'VENDOR')")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(carService.getCarById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENT', 'VENDOR')")
    public CompletableFuture<?> getAllCars() {
        try{

            CompletableFuture<List<Car>> cars = carService.getAllCars();
            List<CarResponse> carResponses = new ArrayList<>();
            cars.get().forEach(car -> {
                carResponses.add(carMapper.toResponse(car));
            });
            return CompletableFuture.completedFuture(carResponses);
        }catch (Exception e){
            return CompletableFuture.completedFuture(ResponseEntity.notFound());
        }
    }


    @PostMapping
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Car> createCar(@RequestBody CarRequest carRequest) {
        try {
            Car savedCar = carService.saveCar(carMapper.toModel(carRequest));
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Void> updateCar(@PathVariable Integer id, @RequestBody CarRequest carRequest) {
        try {
            carService.updateCar(id, carMapper.toModel(carRequest));
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        return carService.deleteCar(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}