package com.example.springtest.exam.controller.response;

import lombok.Getter;

@Getter
public class ExamPassStudentResponse {

    private final String studentName;
    private final Double avgScore;

    public ExamPassStudentResponse(String studentName, Double avgScore) {
        this.studentName = studentName;
        this.avgScore = avgScore;
    }
}