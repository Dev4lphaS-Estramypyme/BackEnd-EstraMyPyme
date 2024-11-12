package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestQuestionRepository;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

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