package dev4lphas.estramypyme.estramypyme_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev4lphas.estramypyme.estramypyme_backend.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
