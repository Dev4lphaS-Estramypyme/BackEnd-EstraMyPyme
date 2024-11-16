package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.tempTestQuestion;

@Repository
public interface tempTestQuestionRepository extends JpaRepository<tempTestQuestion, Long> {
  List<tempTestQuestion> findByTestId(Long testId);
}