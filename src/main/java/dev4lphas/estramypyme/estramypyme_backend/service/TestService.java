package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.repository.AnswerRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // Métodos para Test
    public List<Test> findTestsByCompanyId(Long companyId) {
        return testRepository.findByCompany_Id(companyId);
    }

    public Test saveTestWithAnswers(Test test) {
        for (Answer answer : test.getAnswers()) {
            answer.setTest(test);
        }
        return testRepository.save(test);
    }

    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    public void deleteTestByIdAndCompanyId(Long id, Long companyId) {
        Test test = testRepository.findTestByIdAndCompany_Id(id, companyId);
        if (test != null) {
            testRepository.delete(test);
        } else {
            throw new IllegalArgumentException("Test no encontrado con id: " + id + " y companyId: " + companyId);
        }
    }

    // Métodos para Answer
    public List<Answer> findAnswersByTestIdAndQuestionId(Long testId, Long questionId) {
        return answerRepository.findByTestIdAndQuestionId(testId, questionId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswersByTestIdAndQuestionId(Long testId, Long questionId) {
        List<Answer> answers = answerRepository.findByTestIdAndQuestionId(testId, questionId);
        answerRepository.deleteAll(answers);
    }
}