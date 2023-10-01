package com.example.springtest.model;

import com.example.springtest.calculator.domain.MyCalculator;
import com.example.springtest.exam.domain.StudentPass;
import com.example.springtest.exam.domain.StudentScore;

public class StudentPassFixture {

    public static StudentPass create(StudentScore studentScore) {
        var calculator = new MyCalculator();
        return StudentPass
                .builder()
                .exam(studentScore.getExam())
                .studentName(studentScore.getStudentName())
                .avgScore(calculator
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .add(studentScore.getMathScore().doubleValue())
                        .divide(3.0)
                        .getResult()
                )
                .build();
    }

    public static StudentPass create(String studentName, String exam) {
        return StudentPass
                .builder()
                .exam(exam)
                .studentName(studentName)
                .avgScore(80.0)
                .build();
    }

}
