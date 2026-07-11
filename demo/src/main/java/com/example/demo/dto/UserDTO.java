package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message="username cannot be empty")
    private String username;

    @NotBlank(message="password cannot be empty")
    private String password;

    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public String getUserName()
    {
        return username;
    }

    public void setUserName(String username)
    {
        this.username=username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }
}
