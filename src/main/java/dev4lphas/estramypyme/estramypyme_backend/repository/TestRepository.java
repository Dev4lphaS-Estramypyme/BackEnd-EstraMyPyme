package dev4lphas.estramypyme.estramypyme_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev4lphas.estramypyme.estramypyme_backend.model.Test; 
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
  List<Test> findByCompanyId(Long companyId);
}