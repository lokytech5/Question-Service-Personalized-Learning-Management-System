package com.lokytech.questionservice.repository;

import com.lokytech.questionservice.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
}
