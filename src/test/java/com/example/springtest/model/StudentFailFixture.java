package com.example.springtest.model;

import com.example.springtest.calculator.domain.MyCalculator;
import com.example.springtest.exam.domain.StudentFail;
import com.example.springtest.exam.domain.StudentScore;

public class StudentFailFixture {

    public static StudentFail create(StudentScore studentScore) {
        var calculator = new MyCalculator(0.0);

        return StudentFail
                .builder()
                .studentName(studentScore.getStudentName())
                .exam(studentScore.getExam())
                .avgScore(
                        calculator
                                .add(studentScore.getKorScore().doubleValue())
                                .add(studentScore.getEnglishScore().doubleValue())
                                .add(studentScore.getMathScore().doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();
    }

    public static StudentFail create(String studentName, String exam) {
        return StudentFail
                .builder()
                .studentName(studentName)
                .exam(exam)
                .avgScore(40.0)
                .build();
    }

}
