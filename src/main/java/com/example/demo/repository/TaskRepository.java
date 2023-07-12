package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t LEFT JOIN t.person p WHERE p IS NULL ORDER BY t.period ASC")
    List<Task> findTasksWithoutPersonOrderByPeriod(Pageable pageable);
}
