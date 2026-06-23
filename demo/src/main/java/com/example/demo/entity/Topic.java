package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Topic {
    
    private String name;
    private String status;
    private String category;
    private int hoursSpent;

    @Id
    @GeneratedValue
    private int id;
    

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status=status;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category=category;
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
