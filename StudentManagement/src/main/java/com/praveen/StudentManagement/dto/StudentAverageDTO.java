package com.praveen.StudentManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentAverageDTO {

    private int id;
    private String name;
    private String branch;
    private double average;

    public StudentAverageDTO(int id, String name, double average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }
}
