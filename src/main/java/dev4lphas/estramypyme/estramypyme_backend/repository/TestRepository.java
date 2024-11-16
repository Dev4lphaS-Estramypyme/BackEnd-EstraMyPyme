package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    // Métodos para Test
    List<Test> findByCompanyId(Long companyId);
    Test findTestById(Long id);
    Test findTestByIdAndCompanyId(Long id, Long companyId);
}