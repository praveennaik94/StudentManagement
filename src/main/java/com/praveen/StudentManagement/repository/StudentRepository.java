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


    @Query("""
    SELECT DISTINCT s
    FROM Student s
    LEFT JOIN FETCH s.courses
    WHERE s.name = :name
    """)
    List<Student> findByName(@Param("name") String name);

    Optional<Student> findByNameAndPhoneNumber(
            String name,
            String phoneNumber
    );

    @Query("""
    SELECT DISTINCT s
    FROM Student s
    LEFT JOIN FETCH s.courses
    WHERE s.id = :id
    """)
    Optional<Student> findByIdWithCourses(@Param("id") Long id);
}