package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.model.TestQuestion;
import dev4lphas.estramypyme.estramypyme_backend.service.TestService;
import dev4lphas.estramypyme.estramypyme_backend.service.TestQuestionService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService testQuestionService;

    @GetMapping
    public List<Test> getAllReviews() {
        return testService.findAll();
    }

    // Consultar por el company id
    @GetMapping("/{companyId}")
    public List<Test> getReviewsByCompanyId(@PathVariable Long companyId) {
        return testService.findByCompanyId(companyId);
    }

    // Validar si se pudo crear el test y sino devolver el mensaje de error al front
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTest(@RequestBody Test test) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Guardar el test
            Test createdTest = testService.save(test);

            // Obtener las preguntas activas
            List<TestQuestion> activeQuestions = testQuestionService.findActiveQuestions();

            // Asociar las preguntas activas con el test
            for (TestQuestion question : activeQuestions) {
                question.setTest(createdTest);
                testQuestionService.saveTestQuestion(question);
            }

            response.put("success", true);
            response.put("message", "Test creado exitosamente con preguntas activas asociadas");
            response.put("data", createdTest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Map<String, Object>> deleteTest(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            testService.deleteById(id);
            response.put("success", true);
            response.put("message", "Test eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateTest(@RequestBody Test test) {
        Map<String, Object> response = new HashMap<>();
        try {
            Test updatedTest = testService.update(test);
            response.put("success", true);
            response.put("message", "Test actualizado exitosamente");
            response.put("data", updatedTest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Obtener preguntas asociadas a un test
    @GetMapping("/{testId}/questions")
    public ResponseEntity<Map<String, Object>> getQuestionsByTestId(@PathVariable Long testId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<TestQuestion> questions = testQuestionService.findTestQuestionsByTestId(testId);
            response.put("success", true);
            response.put("data", questions);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}