package com.example.registrycar.controller;

import com.example.registrycar.controller.dtos.CarRequest;
import com.example.registrycar.controller.dtos.CarResponse;
import com.example.registrycar.controller.mapper.CarMapper;
import com.example.registrycar.domain.Car;
import com.example.registrycar.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
 class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarController carController;

    @MockBean
    private CarService carService;

    @MockBean
    private CarMapper carMapper;

    @Test
    @WithMockUser(username = "test", roles = "CLIENT")
    void getCarById_test() throws Exception {
        // Given
        Car car = new Car();
        car.setId(1);
        car.setModel("Ateca");

        // When
        when(carService.getCarById(1)).thenReturn(car);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Ateca"));
    }

    @Test
    @WithMockUser(username = "test", roles = "CLIENT")
    void getAllCars_test() throws Exception {
        // Given
        Car car = new Car();
        car.setId(1);
        car.setModel("Ateca");

        CarResponse carResponse = new CarResponse();
        carResponse.setModel("Ateca");

        when(carService.getAllCars()).thenReturn(CompletableFuture.completedFuture(Collections.singletonList(car)));
        when(carMapper.toResponse(car)).thenReturn(carResponse);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model").value("Ateca"));
    }

    @Test
    @WithMockUser(username = "vendor", roles = "VENDOR")
    void createCar_test() throws Exception {
        // Given
        CarRequest carRequest = new CarRequest();
        carRequest.setModel("Ateca");

        Car car = new Car();
        car.setId(1);
        car.setModel("Ateca");

        when(carMapper.toModel(Mockito.any())).thenReturn(car);
        when(carService.saveCar(Mockito.any())).thenReturn(car);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                        .contentType("application/json")
                        .content("{\"model\": \"Ateca\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Ateca"));
    }

    @Test
    @WithMockUser(username = "vendor", roles = "VENDOR")
    void updateCar_test() throws Exception {
        // No exception, successful update
        this.mockMvc.perform(MockMvcRequestBuilders.put("/cars/1")
                        .contentType("application/json")
                        .content("{\"model\": \"Ateca\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "vendor", roles = "VENDOR")
    void deleteCar_test() throws Exception {
        // Given
        when(carService.deleteCar(1)).thenReturn(true);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/cars/1"))
                .andExpect(status().isOk());
    }
}
