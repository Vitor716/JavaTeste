package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p.name, d.title AS department, COALESCE(SUM(t.duration), 0) AS totalHoursSpent " +
            "FROM Task t " +
            "JOIN t.person p " +
            "JOIN p.department d " +
            "GROUP BY p.name, d.title")
    List<Object[]> getAllPerson();
}
