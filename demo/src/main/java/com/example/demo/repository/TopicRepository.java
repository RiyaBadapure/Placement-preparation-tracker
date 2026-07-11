package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Topic;


@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>
{
    List<Topic> findByCategory(String category);
    List<Topic> findByStatus(String status);
    long countByStatus(String status);
    @Override
    long count();
    long countByCategory(String category);

    List<Topic> findByHoursSpentLessThan(int hoursSpent);
    List<Topic> findByCategoryContaining(String category);
    List<Topic> findByCategoryOrderByHoursSpentDesc(String category);
    Optional<Topic> findTopByOrderByHoursSpentDesc();

    @Query(
        "select t from Topic t "+
        "Where t.status = :status "+
        "And t.hoursSpent> :hoursSpent"
    )
    List<Topic> findByStatusAndHoursSpent(String status,int hoursSpent);

    @Query(
        "select t from Topic t "+
        "Where t.category = :category "+
        "And t.hoursSpent BETWEEN :minHours AND :maxHours "
    )
    List<Topic> findByTopicsByCategoryByHours(String category,int minHours,int maxHours);

    @Query(
        "select coalesce (sum(t.hoursSpent),0) from Topic t"
    )
    Long getTotalHoursSpent();

    
}
