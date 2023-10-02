package com.example.springtest.exam.integration;

import com.example.springtest.exam.infra.StudentScoreRepository;
import com.example.springtest.model.StudentScoreFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringTestApplicationTest extends IntegrationTest {

  @Autowired private StudentScoreRepository studentScoreRepository;

  @Autowired private EntityManager entityManager;

  @Test
  void contextLoader() {
    var studentScore = StudentScoreFixture.passed();
    var savedStudentScore = studentScoreRepository.save(studentScore);

    entityManager.flush();
    entityManager.clear();

    var queryStudentScore =
        studentScoreRepository.findById(savedStudentScore.getId()).orElseThrow();

    Assertions.assertEquals(savedStudentScore, queryStudentScore);
  }
}
