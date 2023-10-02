package com.example.springtest.model;

import com.example.springtest.exam.domain.StudentScore;

public class StudentScoreTestDataBuilder {

  public static StudentScore.StudentScoreBuilder passed() {
    return StudentScore.builder()
        .korScore(80)
        .englishScore(100)
        .mathScore(90)
        .studentName("defaultName")
        .exam("defaultExam");
  }

  public static StudentScore.StudentScoreBuilder failed() {
    return StudentScore.builder()
        .korScore(50)
        .englishScore(40)
        .mathScore(30)
        .studentName("defaultName")
        .exam("defaultExam");
  }
}
