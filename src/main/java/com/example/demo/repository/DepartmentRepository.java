package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT d.title AS department, COUNT(DISTINCT p.personId) AS totalPeople, COUNT(DISTINCT t.taskId) AS totalTasks " +
            "FROM Department d " +
            "LEFT JOIN Person p ON d.departmentId = p.department.departmentId " +
            "LEFT JOIN Task t ON d.departmentId = t.department.departmentId " +
            "GROUP BY d.title")
    List<Object[]> getDepartmentStatistics();
}
