package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.TestAssignment;

@Repository
public interface TestAssignmentRepository extends JpaRepository<TestAssignment, Long> {
  List<TestAssignment> findByTestId(Long testId);
  boolean existsByTestId(Long testId);
  
}