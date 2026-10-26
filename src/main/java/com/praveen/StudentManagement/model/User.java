//
//package com.praveen.StudentManagement.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "student_id",
//            referencedColumnName = "id",
//            unique = true,
//            nullable = false
//    )
//    private Student student;
//
//
//    @Column(nullable = false, unique = true, length = 100)
//    private String username;
//
//    @Column(name = "password_hash", nullable = false)
//    private String password;
//
//    @Column(nullable = false, length = 20)
//    private String role = "USER";
//
//}