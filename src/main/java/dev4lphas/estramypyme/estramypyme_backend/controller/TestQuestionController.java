package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.tempTestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.service.TestQuestionService;

@RestController
@RequestMapping("/test-questions")
public class TestQuestionController {

    @Autowired
    private TestQuestionService testQuestionService;

    // Endpoint para obtener todas las preguntas asociadas a un test por su testId
    @GetMapping("/test/{testId}")
    public ResponseEntity<List<tempTestQuestion>> getQuestionsByTestId(@PathVariable Long testId) {
        List<tempTestQuestion> testQuestions = testQuestionService.findByTestId(testId);
        return ResponseEntity.ok(testQuestions);
    }
}

