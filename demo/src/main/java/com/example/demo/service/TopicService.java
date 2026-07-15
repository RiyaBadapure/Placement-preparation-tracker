package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.dto.GoalDTO;
import com.example.demo.dto.TopicDTO;
import com.example.demo.entity.Topic;
import com.example.demo.exception.TopicNotFoundException;
import com.example.demo.repository.TopicRepository;


@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository)
    {
        this.repository=repository;
    }

    private Topic convertToEntity(TopicDTO dto)
    {
        Topic topic=new Topic();

        topic.setName(dto.getName());
        topic.setCategory(dto.getCategory());
        topic.setStatus(dto.getStatus());
        topic.setHoursSpent(dto.getHoursSpent());
        
        return topic;
    }

    private TopicDTO convertToDTO(Topic topic)
    {
        TopicDTO dto=new TopicDTO();

        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setCategory(topic.getCategory());
        dto.setStatus(topic.getStatus());
        dto.setHoursSpent(topic.getHoursSpent());
        

        return dto;
    }

    public String addTopic(TopicDTO dto)
    {
        Topic topic=convertToEntity(dto);
        
        repository.save(topic);
        return "Topic added";    
    }

    public List<TopicDTO> getAllTopics()
    {
        return repository.findAll()
                        .stream()
                        .map(this::convertToDTO)
                        .toList();
    }

    public TopicDTO getTopicById(int id)
    {
        Topic topic= repository.findById(id)
            .orElseThrow(
            () -> new TopicNotFoundException(
                "Topic with id "+id+"not found"
            )
        );
        return convertToDTO(topic);
    }

    public List<TopicDTO> getByCategory(String category)
    {
        return repository.findByCategory(category)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    public List<TopicDTO> getByStatus(String status)
    {
        return repository.findByStatus(status)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    public String deleteTopic(int id)
    {
        repository.deleteById(id);
        return "Topic deleted";
    }
    
    public TopicDTO updatedTopic(int id,TopicDTO updatedTopic)
    {
        Topic topic=repository.findById(id)
        .orElseThrow(
            ()-> new TopicNotFoundException(
                "Topic with id "+id+" not found"
            )
        );

        topic.setName(updatedTopic.getName());
        topic.setCategory(updatedTopic.getCategory());
        topic.setStatus(updatedTopic.getStatus());
        topic.setHoursSpent(updatedTopic.getHoursSpent());

        repository.save(topic);

        return convertToDTO(topic);
    }

    public long countAllTopics()
    {
        return repository.count();
    }

    public long countByStatus(String status)
    {
        return repository.countByStatus(status);
    }

    public long countByCategory(String category)
    {
        return repository.countByCategory(category);
    }

    public List<TopicDTO> getByHoursSpentLessThan(int hoursSpent)
    {
        return repository.findByHoursSpentLessThan(hoursSpent)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    public List<TopicDTO> getByCategoryContaining(String category)
    {
        return repository.findByCategoryContaining(category)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    public List<TopicDTO> getByCategoryOrderByHoursSpent(String category)
    {
        return repository.findByCategoryOrderByHoursSpentDesc(category)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    public List<TopicDTO> getByStatusAndHoursSpent(String status,int hoursSpent)
    {
        return repository.findByStatusAndHoursSpent(status,hoursSpent)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    public List<TopicDTO> getByTopicsByCategoryByHours(String category,int minHours,int maxHours)
    {
        return repository.findByTopicsByCategoryByHours(category,minHours,maxHours)
                         .stream()
                         .map(this::convertToDTO)
                         .toList();
    }

    private long targetHours=200;

    public String updateGoal(GoalDTO dto)
    {
        targetHours=dto.getTargetHours();
        return "Goal updated successfully";
    }

    public DashboardDTO getDashboard() {

    DashboardDTO dto = new DashboardDTO();

    long totalTopics = repository.count();
    long completedTopics = repository.countByStatus("Completed");

    Long totalHours = repository.getTotalHoursSpent();

    dto.setTargetHours(targetHours);
    dto.setRemainingHours(
        Math.max(targetHours-totalHours,0)
    );

    double goalPercentage= targetHours==0 ? 0 :(totalHours*100.0)/targetHours;

    dto.setGoalPercentage(goalPercentage);

    long safeTotalHours = (totalHours == null) ? 0L : totalHours;

    Topic topTopic = repository.findTopByOrderByHoursSpentDesc()
                                .orElse(null);

    

    dto.setTotalTopics(totalTopics);
    dto.setCompletedTopics(completedTopics);

    dto.setPendingTopics(repository.countByStatus("Not Started"));
    dto.setDsaTopics(repository.countByCategory("DSA"));
    dto.setJavaTopics(repository.countByCategory("JAVA"));
    dto.setOopsTopics(repository.countByCategory("OOPS"));
    dto.setOngoingTopics(repository.countByStatus("On-Going"));

    dto.setTotalHoursSpent(safeTotalHours);

    dto.setAverageHoursSpent(
            totalTopics == 0 ? 0.0 : (double) safeTotalHours / totalTopics
    );

    dto.setMostTimeSpent(
            (topTopic == null) ? "N/A" : topTopic.getName()
    );

    dto.setCompletedPercentage(
            totalTopics == 0 ? 0.0 : (completedTopics * 100.0) / totalTopics
    );

    return dto;
}

   
    
}
