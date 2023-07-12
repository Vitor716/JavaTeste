package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins="*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/post/tarefas")
    public ResponseEntity<Object> saveNewTask(@RequestBody Task task){
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa inválida");
        }

        if (task.getPeriod() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Período é obrigatório");
        }

        if (task.getDepartment() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento é obrigatório");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
    }

    @PutMapping("/put/tarefas/finalizar/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "id")Integer id){
        Optional<Task> optionalTask = taskService.findById(id);

        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        Task task = optionalTask.get();
        task.setFinished(true);

        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(task));
    }

    @GetMapping("/get/tarefas/pendentes")
    public ResponseEntity<List<Task>> getOldTasks (){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findTasksWithoutPersonOrderByPeriod());
    }
}
