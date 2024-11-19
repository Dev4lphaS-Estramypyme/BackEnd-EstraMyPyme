package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    // Métodos para Test
    List<Test> findByCompany_Id(Long companyId);
    Test findTestById(Long id);
    Test findTestByIdAndCompany_Id(Long id, Long companyId);
}
