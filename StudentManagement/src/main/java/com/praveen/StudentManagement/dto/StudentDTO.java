package com.praveen.StudentManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({
        "id",
        "name",
        "branch",
        "phoneNumber",
        "mathsMarks",
        "scienceMarks",
        "englishMarks"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Long id;
    private String name;
    private String branch;
    private Long mathsMarks;
    private Long scienceMarks;
    private Long englishMarks;
    private String phoneNumber;
}
