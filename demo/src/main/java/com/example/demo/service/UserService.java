package com.example.demo.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository repository,PasswordEncoder passwordEncoder,JwtService jwtService)
    {
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }

    private User convertToEntity(UserDTO dto)
    {
        User user=new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    private UserDTO convertToDTO(User user)
    {
        UserDTO dto=new UserDTO();

        dto.setId(user.getId());
        dto.setUserName(user.getUserName());

        return dto;
    }

    public UserDTO registerUser(UserDTO dto)
    {
        if(repository.findByUsername(dto.getUserName()).isPresent())
        {
            throw new RuntimeException("Username already exists");
        }

        User user=convertToEntity(dto);

        repository.save(user);

        return convertToDTO(user);
    }

    public String login(UserDTO dto)
    {
        User user=repository.findByUsername(dto.getUserName())
                            .orElseThrow(
                                ()-> new UserNotFoundException(
                                    "Invalid username or password"
                                )
                            );

                            

        String rawPassword=dto.getPassword();
        String encodedPassword=user.getPassword();

        if(passwordEncoder.matches(rawPassword, encodedPassword))
        {
            String token = jwtService.generateToken(user.getUserName());
            return token;
        }
        else 
        {
            throw new RuntimeException("Invalid username or password");
        }
    }

}
