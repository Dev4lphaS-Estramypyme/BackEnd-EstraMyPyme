package dev4lphas.estramypyme.estramypyme_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.Question;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    // Métodos para Test
    List<Test> findAllTests();
    List<Test> findTestsByCompanyId(Long companyId);
    Test saveTest(Test test);
    void deleteTestById(Long id);
    Test updateTest(Test test);
    Test findTestById(Long id);
    Test findTestByIdAndCompanyId(Long id, Long companyId);

    // Métodos para TestQuestion
    List<TestQuestion> findTestQuestionsByTestId(Long testId);
    void saveTestQuestion(TestQuestion testQuestion);

    // Métodos para Question
    List<Question> findActiveQuestions();

    // Métodos para Answer
    List<Answer> findAnswersByTestIdAndQuestionId(Long testId, Long questionId);
    Answer saveAnswer(Answer answer);
    void deleteAnswerById(Long id);
    List<Answer> findByTestId(Long testId);
    void deleteAll(List<Answer> answers);
}