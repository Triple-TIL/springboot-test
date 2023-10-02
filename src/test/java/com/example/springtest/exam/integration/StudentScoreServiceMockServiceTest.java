package com.example.springtest.exam.integration;

import com.example.springtest.exam.application.StudentScoreService;
import com.example.springtest.exam.controller.response.ExamFailStudentResponse;
import com.example.springtest.exam.controller.response.ExamPassStudentResponse;
import com.example.springtest.exam.domain.StudentFail;
import com.example.springtest.exam.domain.StudentPass;
import com.example.springtest.exam.domain.StudentScore;
import com.example.springtest.exam.infra.StudentFailRepository;
import com.example.springtest.exam.infra.StudentPassRepository;
import com.example.springtest.exam.infra.StudentScoreRepository;
import com.example.springtest.model.StudentFailFixture;
import com.example.springtest.model.StudentPassFixture;
import com.example.springtest.model.StudentScoreFixture;
import com.example.springtest.model.StudentScoreTestDataBuilder;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class StudentScoreServiceMockServiceTest {

  private StudentScoreService studentScoreService;
  private StudentScoreRepository studentScoreRepository;
  private StudentPassRepository studentPassRepository;
  private StudentFailRepository studentFailRepository;

  @BeforeEach
  void setup() {
    studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
    ;
    studentPassRepository = Mockito.mock(StudentPassRepository.class);
    studentFailRepository = Mockito.mock(StudentFailRepository.class);

    studentScoreService =
        new StudentScoreService(
            studentScoreRepository, studentPassRepository, studentFailRepository);
  }

  @Test
  @DisplayName("첫번째 MOCk 테스트")
  void firstSaveScoreMockTest() {
    String givenStudentName = "sss";
    String givenExam = "testExam";
    Integer givenKorScore = 80;
    Integer givenEnglishScore = 100;
    Integer givenMathScore = 40;

    studentScoreService.saveScore(
        givenStudentName, givenExam, givenKorScore, givenEnglishScore, givenMathScore);
  }

  @Test
  @DisplayName("성적 저장 로직 검증 / 60점 이상인 경우")
  void saveScoreMockTest() {

    StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed().build();
    StudentPass expectStudentPass = StudentPassFixture.create(expectStudentScore);
    ArgumentCaptor<StudentScore> studentScoreArgumentCaptor =
        ArgumentCaptor.forClass(StudentScore.class);
    ArgumentCaptor<StudentPass> studentPassArgumentCaptor =
        ArgumentCaptor.forClass(StudentPass.class);

    // when
    studentScoreService.saveScore(
        expectStudentScore.getStudentName(),
        expectStudentScore.getExam(),
        expectStudentScore.getKorScore(),
        expectStudentScore.getEnglishScore(),
        expectStudentScore.getMathScore());

    // then
    Mockito.verify(studentScoreRepository, Mockito.times(1))
        .save(studentScoreArgumentCaptor.capture());
    StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
    Assertions.assertEquals(
        capturedStudentScore.getStudentName(), expectStudentScore.getStudentName());
    Assertions.assertEquals(capturedStudentScore.getExam(), expectStudentScore.getExam());
    Assertions.assertEquals(capturedStudentScore.getKorScore(), expectStudentScore.getKorScore());
    Assertions.assertEquals(
        capturedStudentScore.getEnglishScore(), expectStudentScore.getEnglishScore());
    Assertions.assertEquals(capturedStudentScore.getMathScore(), expectStudentScore.getMathScore());

    Mockito.verify(studentPassRepository, Mockito.times(1))
        .save(studentPassArgumentCaptor.capture());
    StudentPass captorStudentPass = studentPassArgumentCaptor.getValue();
    Assertions.assertEquals(expectStudentPass.getStudentName(), captorStudentPass.getStudentName());
    Assertions.assertEquals(expectStudentPass.getExam(), captorStudentPass.getExam());
    Assertions.assertEquals(expectStudentPass.getAvgScore(), captorStudentPass.getAvgScore());

    Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
  }

  @Test
  @DisplayName("성적 저장 로직 검증 / 60점 미만 경우")
  void saveScoreMockTest2() {
    StudentScore expectStudentScore = StudentScoreFixture.failed();
    StudentFail expectStudentFail = StudentFailFixture.create(expectStudentScore);

    ArgumentCaptor<StudentScore> studentScoreArgumentCaptor =
        ArgumentCaptor.forClass(StudentScore.class);
    ArgumentCaptor<StudentFail> studentFailArgumentCaptor =
        ArgumentCaptor.forClass(StudentFail.class);

    // when
    studentScoreService.saveScore(
        expectStudentScore.getStudentName(),
        expectStudentScore.getExam(),
        expectStudentScore.getKorScore(),
        expectStudentScore.getEnglishScore(),
        expectStudentScore.getMathScore());

    Mockito.verify(studentScoreRepository, Mockito.times(1))
        .save(studentScoreArgumentCaptor.capture());
    StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
    Assertions.assertEquals(
        capturedStudentScore.getStudentName(), expectStudentScore.getStudentName());
    Assertions.assertEquals(capturedStudentScore.getExam(), expectStudentScore.getExam());
    Assertions.assertEquals(capturedStudentScore.getKorScore(), expectStudentScore.getKorScore());
    Assertions.assertEquals(
        capturedStudentScore.getEnglishScore(), expectStudentScore.getEnglishScore());
    Assertions.assertEquals(capturedStudentScore.getMathScore(), expectStudentScore.getMathScore());

    Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
    Mockito.verify(studentFailRepository, Mockito.times(1))
        .save(studentFailArgumentCaptor.capture());

    StudentFail capturedStudentFail = studentFailArgumentCaptor.getValue();
    Assertions.assertEquals(
        expectStudentFail.getStudentName(), capturedStudentFail.getStudentName());
    Assertions.assertEquals(expectStudentFail.getExam(), capturedStudentFail.getExam());
    Assertions.assertEquals(expectStudentFail.getAvgScore(), capturedStudentFail.getAvgScore());
  }

  @Test
  @DisplayName("합격자 명단 가져오기 검증")
  void getPassStudentListTest() {
    final String givenTestExam = "testexam";

    StudentPass expectStudent1 = StudentPassFixture.create("sss", givenTestExam);
    StudentPass expectStudent2 = StudentPassFixture.create("test", givenTestExam);
    StudentPass notExpectStudent3 = StudentPassFixture.create("not", givenTestExam + "01");

    Mockito.when(studentPassRepository.findAll())
        .thenReturn(List.of(expectStudent1, expectStudent2, notExpectStudent3));

    StudentScoreService studentScoreService =
        new StudentScoreService(
            studentScoreRepository, studentPassRepository, studentFailRepository);

    var expectResponse =
        List.of(expectStudent1, expectStudent2).stream()
            .map(pass -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
            .toList();
    List<ExamPassStudentResponse> responses =
        studentScoreService.getPassStudentsList(givenTestExam);

    Assertions.assertIterableEquals(expectResponse, responses);
  }

  @Test
  @DisplayName("불합격 명단 가져오기 검증")
  void getFailStudentTest() {
    final String givenTestExam = "testexam";

    StudentFail expectStudent1 = StudentFailFixture.create("test", givenTestExam);
    StudentFail expectStudent2 = StudentFailFixture.create("sss", givenTestExam);
    StudentFail notExpectStudent3 = StudentFailFixture.create("test", givenTestExam + "01");

    Mockito.when(studentFailRepository.findAll())
        .thenReturn(List.of(expectStudent1, expectStudent2, notExpectStudent3));

    StudentScoreService studentScoreService =
        new StudentScoreService(
            studentScoreRepository, studentPassRepository, studentFailRepository);

    var expectFailList =
        List.of(expectStudent1, expectStudent2).stream()
            .map(fail -> new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore()))
            .toList();

    List<ExamFailStudentResponse> response = studentScoreService.getFailStudentsList(givenTestExam);

    Assertions.assertIterableEquals(expectFailList, response);
  }
}
