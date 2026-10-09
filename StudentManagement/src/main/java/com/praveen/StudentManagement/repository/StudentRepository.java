package com.praveen.StudentManagement.repository;

import com.praveen.StudentManagement.dto.StudentAverageDTO;
import com.praveen.StudentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.name LIKE 'P%'")
    List<Student> findStudentsWhoseNameStartsWithP();
}