package com.example.springtest.exam.application;

import com.example.springtest.calculator.domain.MyCalculator;
import com.example.springtest.exam.controller.response.ExamFailStudentResponse;
import com.example.springtest.exam.controller.response.ExamPassStudentResponse;
import com.example.springtest.exam.domain.StudentFail;
import com.example.springtest.exam.domain.StudentPass;
import com.example.springtest.exam.domain.StudentScore;
import com.example.springtest.exam.infra.StudentFailRepository;
import com.example.springtest.exam.infra.StudentPassRepository;
import com.example.springtest.exam.infra.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentScoreService {

    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;
    private final StudentFailRepository studentFailRepository;

    public void saveScore(String studentName, String exam, Integer korScore, Integer englishScore, Integer mathScore) {
        StudentScore studentScore = StudentScore.builder()
                .exam(exam)
                .studentName(studentName)
                .korScore(korScore)
                .englishScore(englishScore)
                .mathScore(mathScore)
                .build();

        studentScoreRepository.save(studentScore);

        MyCalculator calculator = new MyCalculator(0.0);
        Double avgScore = calculator
                .add(korScore.doubleValue())
                .add(englishScore.doubleValue())
                .add(mathScore.doubleValue())
                .divide(3.0)
                .getResult();

        if (avgScore >= 60) {
            studentPassRepository.save(
                    StudentPass.builder()
                            .exam(exam)
                            .studentName(studentName)
                            .avgScore(avgScore)
                            .build());
        } else {
            studentFailRepository.save(StudentFail.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build());
        }
    }


    public List<ExamPassStudentResponse> getPassStudentsList(String exam) {
        List<StudentPass> studentPasses = studentPassRepository.findAll();

        return studentPasses.stream()
                .filter(pass -> pass.getExam().equals(exam))
                .map(pass -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();
    }

    public List<ExamFailStudentResponse> getFailStudentsList(String exam) {
        List<StudentFail> studentFails = studentFailRepository.findAll();

        return studentFails.stream()
                .filter(fail -> fail.getExam().equals(exam))
                .map(fail -> new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore()))
                .toList();
    }

}
