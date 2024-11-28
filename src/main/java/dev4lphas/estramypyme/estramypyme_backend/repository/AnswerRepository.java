package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByTestIdAndQuestionId(Long testId, Long questionId);
    void deleteByTestIdAndQuestionId(Long testId, Long questionId);
}