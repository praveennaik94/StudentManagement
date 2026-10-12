package com.praveen.StudentManagement.repository;

import com.praveen.StudentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.name LIKE 'P%'")
    List<Student> findStudentsWhoseNameStartsWithP();

    List<Student> findByName(String name);
}