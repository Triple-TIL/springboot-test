package com.example.springtest.exam.integration;

import com.example.springtest.calculator.domain.MyCalculator;
import com.example.springtest.exam.application.StudentScoreService;
import com.example.springtest.model.StudentScoreFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentScoreServiceIntegrationTest extends IntegrationTest {

  @Autowired private StudentScoreService studentScoreService;

  @Autowired private EntityManager entityManager;

  @Test
  void savePassedStudentScoreTest() {
    var studentScore = StudentScoreFixture.passed();

    studentScoreService.saveScore(
        studentScore.getStudentName(),
        studentScore.getExam(),
        studentScore.getKorScore(),
        studentScore.getEnglishScore(),
        studentScore.getMathScore());

    entityManager.flush();
    entityManager.clear();

    var passedStudentResponses = studentScoreService.getPassStudentsList(studentScore.getExam());

    Assertions.assertEquals(1, passedStudentResponses.size());

    var passedStudentResponse = passedStudentResponses.get(0);
    var calculator = new MyCalculator(0.0);
    Assertions.assertEquals(studentScore.getStudentName(), passedStudentResponse.getStudentName());
    Assertions.assertEquals(
        calculator
            .add(studentScore.getKorScore().doubleValue())
            .add(studentScore.getEnglishScore().doubleValue())
            .add(studentScore.getMathScore().doubleValue())
            .divide(3.0)
            .getResult(),
        passedStudentResponse.getAvgScore());
  }

  @Test
  void saveFailedStudentScoreTest() {
    var studentScore = StudentScoreFixture.failed();

    studentScoreService.saveScore(
        studentScore.getStudentName(),
        studentScore.getExam(),
        studentScore.getKorScore(),
        studentScore.getEnglishScore(),
        studentScore.getMathScore());
    entityManager.flush();
    entityManager.clear();

    var failedStudentResponses = studentScoreService.getFailStudentsList(studentScore.getExam());
    Assertions.assertEquals(1, failedStudentResponses.size());

    var failStudentResponse = failedStudentResponses.get(0);
    var calculator = new MyCalculator(0.0);
    Assertions.assertEquals(studentScore.getStudentName(), failStudentResponse.getStudentName());
    Assertions.assertEquals(
        calculator
            .add(studentScore.getKorScore().doubleValue())
            .add(studentScore.getEnglishScore().doubleValue())
            .add(studentScore.getMathScore().doubleValue())
            .divide(3.0)
            .getResult(),
        failStudentResponse.getAvgScore());
  }
}
