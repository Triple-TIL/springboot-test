package com.example.springtest.exam.infra;

import com.example.springtest.exam.domain.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
}
