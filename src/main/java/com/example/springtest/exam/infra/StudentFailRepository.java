package com.example.springtest.exam.infra;

import com.example.springtest.exam.domain.StudentFail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {
}
