package com.example.springtest.exam.controller.response;

import java.util.Objects;
import lombok.Getter;

@Getter
public class ExamPassStudentResponse {

  private final String studentName;
  private final Double avgScore;

  public ExamPassStudentResponse(String studentName, Double avgScore) {
    this.studentName = studentName;
    this.avgScore = avgScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExamPassStudentResponse that = (ExamPassStudentResponse) o;
    return Objects.equals(studentName, that.studentName) && Objects.equals(avgScore, that.avgScore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentName, avgScore);
  }
}
