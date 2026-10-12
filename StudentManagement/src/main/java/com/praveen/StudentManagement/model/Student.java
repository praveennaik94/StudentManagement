package com.praveen.StudentManagement.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
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
    private Set<Course> courses = new HashSet<>();


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getMathsMarks() {
        return mathsMarks;
    }

    public void setMathsMarks(Long mathsMarks) {
        this.mathsMarks = mathsMarks;
    }

    public Long getScienceMarks() {
        return scienceMarks;
    }

    public void setScienceMarks(Long scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    public Long getEnglishMarks() {
        return englishMarks;
    }

    public void setEnglishMarks(Long englishMarks) {
        this.englishMarks = englishMarks;
    }
}

//package com.praveen.StudentManagement.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(
//        name = "student",
//        indexes = {
//                @Index(
//                        name = "idx_student_name_phone",
//                        columnList = "name, phone_number"
//                )
//        }
//)
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String branch;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    private Long mathsMarks;
//    private Long scienceMarks;
//    private Long englishMarks;
//}
