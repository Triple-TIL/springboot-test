package com.example.springtest.exam.controller;

import com.example.springtest.exam.application.StudentScoreService;
import com.example.springtest.exam.controller.request.SaveExamScoreRequest;
import com.example.springtest.exam.controller.response.ExamFailStudentResponse;
import com.example.springtest.exam.controller.response.ExamPassStudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScoreApi {

    private final StudentScoreService studentScoreService;

    @PutMapping("/exam/{exam}/score")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @PathVariable("exam") String exam,
            @RequestBody SaveExamScoreRequest request
    ) {
        studentScoreService.saveScore(
                request.getStudentName(),
                exam,
                request.getKorScore(),
                request.getEnglishScore(),
                request.getMathScore()
        );
    }

    @GetMapping("/exam/{exam}/pass")
    @ResponseStatus(HttpStatus.OK)
    public List<ExamPassStudentResponse> pass(
            @PathVariable String exam
    ) {
        return studentScoreService.getPassStudentsList(exam);
    }

    @GetMapping("/exam/{exam}/fail")
    @ResponseStatus(HttpStatus.OK)
    public List<ExamFailStudentResponse> fail(
            @PathVariable("exam") String exam
    ) {
        return studentScoreService.getFailStudentsList(exam);
    }

}
