package com.praveen.StudentManagement.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.io.Serializable;


@Data
@NoArgsConstructor
@JsonPropertyOrder({
        "id",
        "name",
        "branch",
        "phoneNumber",
        "mathsMarks",
        "scienceMarks",
        "englishMarks",
        "courses"
})
public class StudentCoursesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String branch;
    private String phoneNumber;

    private Long mathsMarks;
    private Long scienceMarks;
    private Long englishMarks;


    private List<CourseDTO> courses;
}