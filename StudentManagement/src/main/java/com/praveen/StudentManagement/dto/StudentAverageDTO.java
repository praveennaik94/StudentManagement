package com.praveen.StudentManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "branch",
        "phoneNumber",
        "average"
})
public class StudentAverageDTO {

    private Long id;
    private String name;
    private String branch;
    private String phoneNumber;
    private double average;

}
