package com.loginwithjwt.example.controller;

import com.loginwithjwt.example.models.User;
import com.loginwithjwt.example.services.UserService;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {


    final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUser() {
        System.out.println("User Displayed");
        return this.userService.getUsers();
    }

    @GetMapping("/current-user")
    public String getLoggedInUse(Principal principal) {
        return principal.getName();
    }



}
