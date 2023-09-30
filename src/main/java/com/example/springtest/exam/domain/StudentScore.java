package com.example.springtest.exam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_score")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Long id;

    @Column(name = "exam")
    private String exam;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "kor_score")
    private Integer korScore;

    @Column(name = "eng_score")
    private Integer englishScore;

    @Column(name = "math_score")
    private Integer mathScore;

    @Builder
    public StudentScore(String exam, String studentName, Integer korScore, Integer englishScore, Integer mathScore) {
        this.exam = exam;
        this.studentName = studentName;
        this.korScore = korScore;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
    }
}
