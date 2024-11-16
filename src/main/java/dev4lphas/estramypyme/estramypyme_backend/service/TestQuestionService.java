package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.tempTestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.repository.AnswerRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.tempTestQuestionRepository;

@Service
public class TestQuestionService {

    @Autowired
    private tempTestQuestionRepository testQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // Método para obtener todas las preguntas asociadas a un test por su testId
    public List<tempTestQuestion> findByTestId(Long testId) {
        List<tempTestQuestion> testQuestions = testQuestionRepository.findByTestId(testId);
        for (tempTestQuestion tq : testQuestions) {
            List<Answer> answers = answerRepository.findByTestIdAndQuestionId(testId, tq.getQuestion().getId());
            tq.getQuestion().setAnswers(answers);
        }
        return testQuestions;
    }
}