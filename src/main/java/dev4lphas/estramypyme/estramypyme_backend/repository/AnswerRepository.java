package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByTestIdAndQuestionId(Long testId, Long questionId);
}