package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.Question;
import dev4lphas.estramypyme.estramypyme_backend.service.QuestionService;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping("/active")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
    public ResponseEntity<List<Question>> getActiveQuestions() {
        List<Question> questions = questionService.findActiveQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.POST})
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question newQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/updatetext/{id}")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.PUT})
    public ResponseEntity<Question> updateTextQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updateQuestion = questionService.updateTextQuestion(id, question);
        if (updateQuestion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @PutMapping("/updatestatus/{id}")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.PUT})
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updateQuestion = questionService.statusQuestion(id, question);
        if (updateQuestion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.DELETE})
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

