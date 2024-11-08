package dev4lphas.estramypyme.estramypyme_backend.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // Crear Test
    public Test createTest(Test test) {
        List<Test> previousTests = testRepository.findByCompanyId(test.getCompanyId());
        Date sixMonthsAgo = Date.from(LocalDate.now().minusMonths(6).atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Verificar si el último test fue realizado en los últimos 6 meses
        if (previousTests.stream().anyMatch(t -> t.getTestDate().after(sixMonthsAgo))) {
            throw new IllegalStateException("No se puede realizar un nuevo test antes de 6 meses.");
        }

        test.setTestDate(new Date()); // fecha actual
        test.setIsReviewed(false); // campo isReviewed en FALSE por defecto
        return testRepository.save(test);
    }

    // Buscar Tests por Empresa: Devuelve todos los tests asociados a companyId
    public List<Test> getTestsByCompanyId(Long companyId) {
        return testRepository.findByCompanyId(companyId);
    }

    // Editar Información de Test
    public Test updateTest(Test updatedTest) {
        Optional<Test> existingTest = testRepository.findById(updatedTest.getId());
        if (existingTest.isPresent()) {
            Test test = existingTest.get();
            test.setCompanyId(updatedTest.getCompanyId());  // solo actualiza el campo CompanyId
            return testRepository.save(test);
        }
        throw new EntityNotFoundException("Test no encontrado"); // Si el test no se encuentra, muestra una excepción
    }
}