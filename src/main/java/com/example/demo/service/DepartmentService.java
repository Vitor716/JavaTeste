package com.example.demo.service;

import com.example.demo.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Object[]> findDepartments(){
        return departmentRepository.getDepartmentStatistics();
    }
}
