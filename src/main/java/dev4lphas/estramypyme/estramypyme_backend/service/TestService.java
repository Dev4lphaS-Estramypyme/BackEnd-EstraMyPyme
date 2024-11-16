package dev4lphas.estramypyme.estramypyme_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public List<Test> findByCompanyId(Long companyId) {
        return testRepository.findByCompanyId(companyId);
    }

    public Test save(Test test) {
        // Asegurarse de que isReview sea false al crear una nueva Review
        if (test.getId() == null) {
            test.setIsReview(false);
        }

        List<Test> existingTest = testRepository.findByCompanyId(test.getCompanyId());
        if (!existingTest.isEmpty()) {
            // Verificar si el Review más reciente tiene menos de 6 meses
            Test latestTest = existingTest.get(0);
            for (Test r : existingTest) {
                if (r.getDate().isAfter(latestTest.getDate())) {
                    latestTest = r;
                }
            }
            LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
            if (latestTest.getDate().isAfter(sixMonthsAgo)) {
                throw new RuntimeException("No se puede crear un nuevo Test para esta identificación porque ya existe uno creado hace menos de 6 meses.");
            }
        }

        return testRepository.save(test);
    }

    public void deleteById(Long id) {
        if (!testRepository.existsById(id)) {
            throw new IllegalArgumentException("Test no encontrado con id: " + id);
        }
        testRepository.deleteById(id);
    }

    public Test update(Test test) {
        if (test.getId() == null) {
            throw new IllegalArgumentException("El ID del Test no puede ser nulo para actualizar");
        }
        if (!testRepository.existsById(test.getId())) {
            throw new IllegalArgumentException("Test no encontrado con id: " + test.getId());
        }
        return testRepository.save(test);
    }

    public Test findById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    public boolean isReviewed(Long testId) {
        return testRepository.findById(testId)
                             .map(Test::isReviewed)
                             .orElse(false);
    }
}