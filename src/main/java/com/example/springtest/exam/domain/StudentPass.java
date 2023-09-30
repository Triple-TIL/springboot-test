package com.example.springtest.exam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "student_pass")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentPass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam")
    private String exam;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "avg_score")
    private Double avgScore;

    @Builder
    public StudentPass(String exam, String studentName, Double avgScore) {
        this.exam = exam;
        this.studentName = studentName;
        this.avgScore = avgScore;
    }
}
