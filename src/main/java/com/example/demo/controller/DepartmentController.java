package com.example.demo.controller;

import com.example.demo.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins="*")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/get/departamentos")
    public ResponseEntity<List<Object[]>> getDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findDepartments());
    }
}
