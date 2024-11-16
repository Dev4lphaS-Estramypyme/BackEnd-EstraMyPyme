package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.Question;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    // Métodos para Test
    public List<Test> findAllTests() {
        return testRepository.findAllTests();
    }

    public List<Test> findTestsByCompanyId(Long companyId) {
        return testRepository.findTestsByCompanyId(companyId);
    }

    public Test saveTest(Test test) {
        return testRepository.saveTest(test);
    }

    public Test updateTest(Test test) {
        return testRepository.updateTest(test);
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
        return testRepository.findTestQuestionsByTestId(testId);
    }

    public void saveTestQuestion(TestQuestion testQuestion) {
        testRepository.saveTestQuestion(testQuestion);
    }

    // Métodos para Question
    public List<Question> findActiveQuestions() {
        return testRepository.findActiveQuestions();
    }

    // Métodos para Answer
    public List<Answer> findAnswersByTestIdAndQuestionId(Long testId, Long questionId) {
        return testRepository.findAnswersByTestIdAndQuestionId(testId, questionId);
    }

    public Answer saveAnswer(Answer answer) {
        return testRepository.saveAnswer(answer);
    }

    public void deleteAnswerById(Long id) {
        testRepository.deleteAnswerById(id);
    }

    public void deleteAnswersByTestIdAndQuestionId(Long testId, Long questionId) {
        List<Answer> answers = testRepository.findAnswersByTestIdAndQuestionId(testId, questionId);
        testRepository.deleteAll(answers);
    }

    public void deleteAllAnswersByTestId(Long testId) {
        List<Answer> answers = testRepository.findByTestId(testId);
        testRepository.deleteAll(answers);
    }
}