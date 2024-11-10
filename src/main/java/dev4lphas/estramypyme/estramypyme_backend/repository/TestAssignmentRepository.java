package dev4lphas.estramypyme.estramypyme_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.TestAssignment;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestAssignmentRepository extends JpaRepository<TestAssignment, Long> {
  List<TestAssignment> findByTestId(Long testId);

  Optional<TestAssignment> findById(Long id);

  boolean existsByTestId(Long testId);
  
}