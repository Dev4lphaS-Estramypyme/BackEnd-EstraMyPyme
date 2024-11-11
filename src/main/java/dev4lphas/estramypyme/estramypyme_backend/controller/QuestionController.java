package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev4lphas.estramypyme.estramypyme_backend.model.Question;
import dev4lphas.estramypyme.estramypyme_backend.repository.QuestionRepository;
import dev4lphas.estramypyme.estramypyme_backend.service.QuestionService;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("")
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    // Creación de Question.
    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question newQuestion = questionService.createQuestion(question);
        if (newQuestion == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/updatetext/{id}")
    public ResponseEntity<Question> updateTextQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updateQuestion = questionService.updateTextQuestion(id, question);

        if (updateQuestion == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updateQuestion = questionService.statusQuestion(id, question);

        if (updateQuestion == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Long id) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);
        if (existingQuestion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Question with ID " + id + " not found.");
        }
        try {
            return ResponseEntity.ok("Cannot delete question with ID " + id
                    + " But if necessary you can deactivate it, change status for active: false ");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with ID " + id + " not found.");
        }
    }

}