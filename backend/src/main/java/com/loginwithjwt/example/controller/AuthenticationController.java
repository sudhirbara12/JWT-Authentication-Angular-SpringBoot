package com.loginwithjwt.example.controller;

import com.loginwithjwt.example.dto.JwtRequest;
import com.loginwithjwt.example.dto.JwtResponse;
import com.loginwithjwt.example.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping(value = "/signin", consumes="application/json",produces = "application/json")
    public ResponseEntity<JwtResponse> signin(@RequestBody JwtRequest jwtRequest){
//        System.out.println("asfdfdf");
        return authenticationService.authenticateUser(jwtRequest);
    }
}
