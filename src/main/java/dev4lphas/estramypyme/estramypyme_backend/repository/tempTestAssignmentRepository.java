package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.tempTestAssignment;

@Repository
public interface tempTestAssignmentRepository extends JpaRepository<tempTestAssignment, Long> {
  List<tempTestAssignment> findByTestId(Long testId);
  boolean existsByTestId(Long testId);
  
}