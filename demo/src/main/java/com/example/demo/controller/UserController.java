package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService service;

    public UserController(UserService service)
    {
        this.service=service;
    }


    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody @Valid UserDTO dto)
    {
        return service.registerUser(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserDTO dto)
    {
        return service.login(dto);
    }

    
}
