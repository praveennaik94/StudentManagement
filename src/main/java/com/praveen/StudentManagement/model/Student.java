package com.praveen.StudentManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_student_name_phone",
                        columnNames = {"name", "phone_number"}
                )
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String branch;

    @Column(name = "phone_number")
    private String phoneNumber;
    private Long mathsMarks;
    private Long scienceMarks;
    private Long englishMarks;


    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();



}

