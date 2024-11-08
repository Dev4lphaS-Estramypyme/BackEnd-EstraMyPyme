package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;

public interface TestRepository extends JpaRepository<Test, String> {
    List<Test> findByCompanyId(Long companyId); // Cambiado a Long
}