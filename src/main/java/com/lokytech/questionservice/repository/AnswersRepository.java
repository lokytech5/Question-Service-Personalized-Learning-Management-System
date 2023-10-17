package com.lokytech.questionservice.repository;

import com.lokytech.questionservice.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
}
