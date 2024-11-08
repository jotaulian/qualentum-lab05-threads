package com.example.registrycar.service;

import com.example.registrycar.controller.dtos.JwtResponse;
import com.example.registrycar.controller.dtos.LoginRequest;
import com.example.registrycar.controller.dtos.SignUpRequest;

public interface AuthenticationService {

    public JwtResponse signup(SignUpRequest request) throws Exception;

    public JwtResponse login(LoginRequest request);

}
