package com.praveen.StudentManagement.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "name",
        "branch",
        "mathsMarks",
        "scienceMarks",
        "englishMarks"
})
public class StudentDTO {

    private String name;
    private String branch;
    private Integer mathsMarks;
    private Integer scienceMarks;
    private Integer englishMarks;

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

    public Integer getMathsMarks() {
        return mathsMarks;
    }

    public void setMathsMarks(Integer mathsMarks) {
        this.mathsMarks = mathsMarks;
    }

    public Integer getScienceMarks() {
        return scienceMarks;
    }

    public void setScienceMarks(Integer scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    public Integer getEnglishMarks() {
        return englishMarks;
    }

    public void setEnglishMarks(Integer englishMarks) {
        this.englishMarks = englishMarks;
    }
}
