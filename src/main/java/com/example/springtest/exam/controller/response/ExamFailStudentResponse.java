package com.example.springtest.exam.controller.response;

import lombok.Getter;

@Getter
public class ExamFailStudentResponse {

    private final String studentName;
    private final Double avgScore;

    public ExamFailStudentResponse(String studentName, Double avgScore) {
        this.studentName = studentName;
        this.avgScore = avgScore;
    }
}
