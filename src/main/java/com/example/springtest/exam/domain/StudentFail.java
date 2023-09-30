package com.example.springtest.exam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "student_fail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentFail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_fail_id")
    private Long id;

    @Column(name = "exam")
    private String exam;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "avg_score")
    private Double avgScore;

    @Builder
    public StudentFail(String exam, String studentName, Double avgScore) {
        this.exam = exam;
        this.studentName = studentName;
        this.avgScore = avgScore;
    }
}
