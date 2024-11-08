package dev4lphas.estramypyme.estramypyme_backend.controller;
import dev4lphas.estramypyme.estramypyme_backend.service.TestService;
import dev4lphas.estramypyme.estramypyme_backend.model.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test> getAllReviews() {
        return testService.findAll();
    }


    //Consultar por el company id
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

  

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
    //     reviewService.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }
}