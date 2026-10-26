package com.praveen.StudentManagement.controller;

import com.praveen.StudentManagement.dto.StudentAverageDTO;
import com.praveen.StudentManagement.dto.StudentDTO;
import com.praveen.StudentManagement.model.Student;
import com.praveen.StudentManagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(
            @RequestBody Student student) {

        Student saved = studentService.addStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){

        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){

        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> partialUpdateStudent(@PathVariable Long id, @RequestBody Student student){

        return ResponseEntity.ok(studentService.patchStudent(id, student));
    }

    @GetMapping("/{id}/average")
    public ResponseEntity<Double> getAverage(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getAverage(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentAverageDTO>> getStudentsWithAverage() {

        return ResponseEntity.ok(
                studentService.getStudentsWithAverage()
        );
    }
    @GetMapping("/name")
    public ResponseEntity<List<StudentDTO>> getStudentByName(
            @RequestParam String name) {

        return ResponseEntity.ok(studentService.getStudentByName(name));
    }

    @GetMapping("/highest-average")
    public ResponseEntity<StudentAverageDTO> getHighestAverageByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                studentService.getHighestAverageByName(name));
    }

}
