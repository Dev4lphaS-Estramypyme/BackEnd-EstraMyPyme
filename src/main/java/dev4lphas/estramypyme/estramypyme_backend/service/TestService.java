package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestQuestionRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.AnswerRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // Métodos para Test
    public List<Test> findAllTests() {
        return testRepository.findAll();
    }

    public List<Test> findTestsByCompanyId(Long companyId) {
        return testRepository.findByCompanyId(companyId);
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    public Test findTestById(Long id) {
        return testRepository.findTestById(id);
    }

    public boolean isReviewed(Long testId) {
        return testRepository.findById(testId)
                             .map(Test::isReviewed)
                             .orElse(false);
    }

    public void deleteTestByIdAndCompanyId(Long id, Long companyId) {
        Test test = testRepository.findTestByIdAndCompanyId(id, companyId);
        if (test != null) {
            testRepository.delete(test);
        } else {
            throw new IllegalArgumentException("Test no encontrado con id: " + id + " y companyId: " + companyId);
        }
    }

    // Métodos para TestQuestion
    public List<TestQuestion> findTestQuestionsByTestId(Long testId) {
        return testQuestionRepository.findByTestId(testId);
    }

    public void saveTestQuestion(TestQuestion testQuestion) {
        testQuestionRepository.save(testQuestion);
    }

    // Métodos para Answer
    public List<Answer> findAnswersByTestIdAndQuestionId(Long testId, Long questionId) {
        return answerRepository.findByTestIdAndQuestionId(testId, questionId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswerById(Long id) {
        answerRepository.deleteById(id);
    }

    public void deleteAnswersByTestIdAndQuestionId(Long testId, Long questionId) {
        List<Answer> answers = answerRepository.findByTestIdAndQuestionId(testId, questionId);
        answerRepository.deleteAll(answers);
    }

    public void deleteAllAnswersByTestId(Long testId) {
        List<Answer> answers = answerRepository.findByTestId(testId);
        answerRepository.deleteAll(answers);
    }
}