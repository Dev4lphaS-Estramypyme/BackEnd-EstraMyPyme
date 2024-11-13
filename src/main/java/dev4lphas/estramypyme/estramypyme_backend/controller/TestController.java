package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

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
            Test createdTest = testService.save(test);
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

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Map<String, Object>> deleteTest(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            testService.deleteById(id);
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
            Test updatedTest = testService.update(test);
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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTestById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Test test = testService.findById(id);
        if (test == null) {
            response.put("success", false);
            response.put("message", "Test no encontrado con id: " + id);
            return ResponseEntity.status(404).body(response);
        }
        response.put("success", true);
        response.put("data", test);
        return ResponseEntity.ok(response);
    }
}

