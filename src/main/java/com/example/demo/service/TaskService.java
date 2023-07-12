package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TaskRepository taskRepository;

    @Transactional
    public Task save(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Integer id){
        return taskRepository.findById(id);
    }

    public List<Task> findTasksWithoutPersonOrderByPeriod(){
        Pageable pageable = PageRequest.of(0, 3);
        return taskRepository.findTasksWithoutPersonOrderByPeriod(pageable);
    }
}
