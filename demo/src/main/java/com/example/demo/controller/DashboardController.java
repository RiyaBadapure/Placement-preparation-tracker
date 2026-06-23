package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TopicService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final TopicService service;

    public DashboardController(TopicService service)
    {
        this.service=service;
    }

    @GetMapping("/stats")
    public DashboardStats getstats()
    {
        long total=service.countAllTopics();
        long completed=service.countByStatus("Completed");
        long ongoing=service.countByStatus("on-going");
        long notStarted=service.countByStatus("Not started");

        return new DashboardStats(total, completed, ongoing, notStarted);
    }

    
}


