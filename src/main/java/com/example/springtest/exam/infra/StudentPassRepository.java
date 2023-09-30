package com.example.springtest.exam.infra;

import com.example.springtest.exam.domain.StudentPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPassRepository extends JpaRepository<StudentPass, Long> {
}
