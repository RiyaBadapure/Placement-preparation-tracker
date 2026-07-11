package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository)
    {
        this.repository=repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        System.out.println("Searching database for: " + username);
        com.example.demo.entity.User user=repository.findByUsername(username)
                            .orElseThrow(
                                ()-> new UserNotFoundException("Invalid username or password")
                            );

                        return new User(
                            user.getUserName(),
                            user.getPassword(),
                            List.of(
                                new SimpleGrantedAuthority("ROLE_USER")
                            )

                        );
    }
}
