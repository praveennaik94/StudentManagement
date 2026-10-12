package com.praveen.StudentManagement.service;

import com.praveen.StudentManagement.config.ModelMapperConfig;
import com.praveen.StudentManagement.dto.StudentAverageDTO;
import com.praveen.StudentManagement.dto.StudentDTO;
import com.praveen.StudentManagement.exception.InvalidStudentException;
import com.praveen.StudentManagement.exception.StudentNotFoundException;
import com.praveen.StudentManagement.model.Student;
import com.praveen.StudentManagement.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository,
                          ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    public Student addStudent(Student student) {

        if (student.getMathsMarks() < 0 || student.getMathsMarks() > 100 ||
                student.getScienceMarks() < 0 || student.getScienceMarks() > 100 ||
                student.getEnglishMarks() < 0 || student.getEnglishMarks() > 100) {

            throw new InvalidStudentException("Marks must be between 0 and 100");
        }
        return studentRepository.save(student);
    }

    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id
                        ));

        return modelMapper.map(student, StudentDTO.class);
    }

    public Student updateStudent(Long id, Student student) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id));



        existing.setName(student.getName());
        existing.setBranch(student.getBranch());
        existing.setPhoneNumber(student.getPhoneNumber());
        existing.setMathsMarks(student.getMathsMarks());
        existing.setScienceMarks(student.getScienceMarks());
        existing.setEnglishMarks(student.getEnglishMarks());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student not found with id: " + id
            );
        }

        studentRepository.deleteById(id);
    }

    public Student patchStudent(Long id, Student student) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id));

        if (student.getName() != null) {
            existing.setName(student.getName());
        }

        if (student.getBranch() != null) {
            existing.setBranch(student.getBranch());
        }

        if (student.getMathsMarks() != 0) {
            existing.setMathsMarks(student.getMathsMarks());
        }

        if (student.getScienceMarks() != 0) {
            existing.setScienceMarks(student.getScienceMarks());
        }

        if (student.getEnglishMarks() != 0) {
            existing.setEnglishMarks(student.getEnglishMarks());
        }

        return studentRepository.save(existing);
    }

    public double getAverage(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id));

        return (student.getMathsMarks()
                + student.getScienceMarks()
                + student.getEnglishMarks()) / 3.0;
    }

    public List<StudentAverageDTO> getStudentsWithAverage() {

        List<Student> students =
                studentRepository.findStudentsWhoseNameStartsWithP();

        return students.stream()
                .filter(student -> calculateAverage(student) > 80)
                .map(student -> new StudentAverageDTO(
                        student.getId(),
                         student.getName(),
                        student.getBranch(),
                        student.getPhoneNumber(),
                        calculateAverage(student)
                ))
                .toList();
    }

    private double calculateAverage(Student student) {

        return (student.getMathsMarks()
                + student.getScienceMarks()
                + student.getEnglishMarks()) / 3.0;
    }

    public List<StudentDTO> getStudentByName(String name) {
        return studentRepository.findByName(name)
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public StudentAverageDTO getHighestAverageByName(String name) {

        Student student = studentRepository.findByName(name)
                .stream()
                .max(Comparator.comparingDouble(this::calculateAverage))
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with name: " + name));

        StudentAverageDTO dto =
                modelMapper.map(student, StudentAverageDTO.class);

        dto.setAverage(calculateAverage(student));

        return dto;
    }
}

//
