package com.example.springtest.exam.controller.request;

import lombok.Getter;

@Getter
public class SaveExamScoreRequest {

  private final String studentName;
  private final Integer korScore;
  private final Integer englishScore;
  private final Integer mathScore;

  public SaveExamScoreRequest(
      String studentName, Integer korScore, Integer englishScore, Integer mathScore) {
    this.studentName = studentName;
    this.korScore = korScore;
    this.englishScore = englishScore;
    this.mathScore = mathScore;
  }
}
