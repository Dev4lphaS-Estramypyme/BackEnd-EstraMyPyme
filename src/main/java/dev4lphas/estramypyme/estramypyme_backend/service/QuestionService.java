package dev4lphas.estramypyme.estramypyme_backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Question;
import dev4lphas.estramypyme.estramypyme_backend.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Listar todos las preguntas
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    // Creación de Question
    public Question createQuestion(Question question) {
        question.setCreatedAt(LocalDate.now());
        question.setActive(true);
        return questionRepository.save(question);

    }

    // Actualizar text question
    public Question updateTextQuestion(Long id, Question question) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);

        if (existingQuestion == null)
            return null;

        existingQuestion
                .setQuestion(question.getQuestion() == null ? existingQuestion.getQuestion() : question.getQuestion());
        existingQuestion.setCreatedAt(existingQuestion.getCreatedAt());
        existingQuestion.setActive(existingQuestion.getActive());

        return questionRepository.save(existingQuestion);
    }

    // Actualizar status question
    public Question statusQuestion(Long id, Question question) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);

        if (existingQuestion == null)
            return null;

        existingQuestion.setQuestion(existingQuestion.getQuestion());
        existingQuestion.setCreatedAt(existingQuestion.getCreatedAt());
        existingQuestion.setActive(question.getActive() == null ? existingQuestion.getActive() : question.getActive());

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

}
