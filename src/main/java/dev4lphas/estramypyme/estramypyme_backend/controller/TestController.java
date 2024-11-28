package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.service.TestService;

@RestController
@RequestMapping("/tests")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/{companyId}")
    public List<Test> getTestsByCompanyId(@PathVariable Long companyId) {
        return testService.findTestsByCompanyId(companyId);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createTest(@RequestBody Test test) {
        Map<String, Object> response = new HashMap<>();
        try {
            Test createdTest = testService.saveTestWithAnswers(test);
            response.put("success", true);
            response.put("message", "Test creado exitosamente");
            response.put("data", createdTest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/deleteByIdAndCompanyId/{id}/{companyId}")
    public ResponseEntity<Map<String, Object>> deleteTestByIdAndCompanyId(@PathVariable Long id, @PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            testService.deleteTestByIdAndCompanyId(id, companyId);
            response.put("success", true);
            response.put("message", "Test eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
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
            Test updatedTest = testService.updateTest(test);
            response.put("success", true);
            response.put("message", "Test actualizado exitosamente");
            response.put("data", updatedTest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/answers/test/{testId}/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByTestIdAndQuestionId(@PathVariable Long testId, @PathVariable Long questionId) {
        List<Answer> answers = testService.findAnswersByTestIdAndQuestionId(testId, questionId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping("/answers")
    public ResponseEntity<Map<String, Object>> createAnswers(@RequestBody List<Answer> answers) {
        Map<String, Object> response = new HashMap<>();
        try {
            for (Answer answer : answers) {
                testService.saveAnswer(answer);
            }
            response.put("success", true);
            response.put("message", "Respuestas creadas exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/answers/test/{testId}/question/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteAnswerByTestIdAndQuestionId(@PathVariable Long testId, @PathVariable Long questionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            testService.deleteAnswersByTestIdAndQuestionId(testId, questionId);
            response.put("success", true);
            response.put("message", "Respuestas eliminadas con éxito");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}