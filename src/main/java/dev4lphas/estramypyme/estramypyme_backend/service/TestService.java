package dev4lphas.estramypyme.estramypyme_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestQuestionRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    public boolean isReviewed(Long testId) {
        Test test = testRepository.findById(testId).orElse(null);
        return test != null && test.getIsReview();
    }

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
        testRepository.deleteById(id);
    }

    public Test update(Test test) {
        if (test.getId() == null) {
            throw new IllegalArgumentException("El ID del Test no puede ser nulo para actualizar");
        }
        return testRepository.save(test);
    }

    // Métodos para TestQuestion
    public TestQuestion saveTestQuestion(TestQuestion testQuestion) {
        return testQuestionRepository.save(testQuestion);
    }

    public List<TestQuestion> findTestQuestionsByTestId(Long testId) {
        return testQuestionRepository.findByTestId(testId);
    }

    public Optional<TestQuestion> findTestQuestionById(Long id) {
        return testQuestionRepository.findTestQuestionById(id);
    }

    public void deleteTestQuestionById(Long id) {
        testQuestionRepository.deleteById(id);
    }

    // Método para obtener preguntas activas
    public List<TestQuestion> findActiveQuestions() {
        return testQuestionRepository.findByActiveTrue();
    }
}