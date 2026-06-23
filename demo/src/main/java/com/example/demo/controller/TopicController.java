package com.example.demo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.dto.TopicDTO;
import com.example.demo.service.TopicService;

import jakarta.validation.Valid;



@RestController
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service)
    {
        this.service=service;
    }

    @PostMapping("/topics")
    public String addTopic(@RequestBody @Valid TopicDTO dto)
    {
        return service.addTopic(dto);
    }

    @GetMapping("/topics")
    public List<TopicDTO> getAllTopics()
    {
        return service.getAllTopics();
    }

    @GetMapping("/topics/{id}")
    public TopicDTO getTopicById(@PathVariable int id)
    {
        return service.getTopicById(id);
    }

    @GetMapping("/topics/category/{category}")
    public List<TopicDTO> getByCategory(@PathVariable String category)
    {
        return service.getByCategory(category);
    }

    @GetMapping("/topics/status/{status}")
    public List<TopicDTO> getByStatus(@PathVariable String status)
    {
        return service.getByStatus(status);
    }
    
    @DeleteMapping("/topics/{id}")
    public String deleteTopic(@PathVariable int id)
    {
        return service.deleteTopic(id);
    }


    @PutMapping("/topics/{id}")
    public TopicDTO updatedTopic(@PathVariable int id,@RequestBody @Valid TopicDTO updatedTopic)
    {
        return service.updatedTopic(id,updatedTopic);
    }
    
    @GetMapping("/topics/category-count/{category}")
    public long countByCategory(@PathVariable String category)
    {
        return service.countByCategory(category);
    }

    @GetMapping("/topics/hours-less-than/{hoursSpent}")
    public List<TopicDTO> getByHoursSpentLessThan(@PathVariable int hoursSpent)
    {
        return service.getByHoursSpentLessThan(hoursSpent);
    }

    @GetMapping("/topics/category-like/{category}")
    public List<TopicDTO> getByCategoryContaining(@PathVariable String category)
    {
        return service.getByCategoryContaining(category);
    }

    @GetMapping("/topics/category/{category}/hours-desc")
    public List<TopicDTO> getByCategoryOrderByHoursSpent(@PathVariable String category)
    {
        return service.getByCategoryOrderByHoursSpent(category);
    }

    @GetMapping("/topics/status/{status}/HoursSpent/{hoursSpent}")
    public List<TopicDTO> getByStatusAndHoursSpent(@PathVariable String status,@PathVariable int hoursSpent)
    {
        return service.getByStatusAndHoursSpent(status,hoursSpent);
    }

    @GetMapping("/topics/category/{category}/{minHours}/{maxHours}")
    public List<TopicDTO> getByTopicsByCategoryByHours(@PathVariable String category,@PathVariable int minHours,@PathVariable int maxHours)
    {
        return service.getByTopicsByCategoryByHours(category,minHours,maxHours);
    }

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard()
    {
        return service.getDashboard();
    }
}
