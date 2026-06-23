package com.example.demo.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TopicDTO {

    @NotBlank(message="Name cannot be empty")
    private String name;
    @NotBlank(message="Category cannot be empty")
    private String category;
    @NotBlank(message="Status cannot be empty")
    private String status;
    @Min(value=0,message="Hours spent cannot be negative")
    private int hoursSpent;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    
    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category=category;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status=status;
    }

    public int getHoursSpent()
    {
        return hoursSpent;
    }

    public void setHoursSpent(int hoursSpent)
    {
        this.hoursSpent=hoursSpent;
    }
}
