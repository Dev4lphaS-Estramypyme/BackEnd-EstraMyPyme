package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.repository.AnswerRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestQuestionRepository;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // Método para obtener todas las preguntas asociadas a un test por su testId
    public List<TestQuestion> findByTestId(Long testId) {
        List<TestQuestion> testQuestions = testQuestionRepository.findByTestId(testId);
        for (TestQuestion tq : testQuestions) {
            List<Answer> answers = answerRepository.findByTestIdAndQuestionId(testId, tq.getQuestion().getId());
            tq.getQuestion().setAnswers(answers);
        }
        return testQuestions;
    }
}