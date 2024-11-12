package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {
  List<TestQuestion> findByTestId(Long testId);
  Optional<TestQuestion> findTestQuestionById(Long id);
  
  List<TestQuestion> findByActiveTrue();
}