package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev4lphas.estramypyme.estramypyme_backend.model.TestAssignment;
import dev4lphas.estramypyme.estramypyme_backend.service.TestAssignmentService;
import dev4lphas.estramypyme.estramypyme_backend.service.TestService;
import dev4lphas.estramypyme.estramypyme_backend.service.UserService;

@RestController
@RequestMapping("/testAssignments")
public class TestAssignmentsController {

    @Autowired
    private TestAssignmentService testAssignmentService;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<TestAssignment> getAllReviews() {
        return testAssignmentService.findAll();
    }

    //Consultar por el company id
    @GetMapping("/{id}")
    public Optional<TestAssignment> getAssigmentById(@PathVariable Long id) {
        return testAssignmentService.findById(id);
    }

    // Validar si se pudo crear el test y sino devolver el mensaje de error al front
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTest(@RequestBody TestAssignment testAssignment) {
        Map<String, Object> response = new HashMap<>();

        // Validar si is_reviewed en la tabla test está en false
        if (testService.isReviewed(testAssignment.getTestId())) {
            response.put("success", false);
            response.put("message", "No se puede crear el TestAssignment porque is_reviewed en la tabla test está en true");
            return ResponseEntity.badRequest().body(response);
        }

        // Validar si el usuario es Admin o Teacher
        if (!userService.isAdminOrTeacher(testAssignment.getUserId())) {
            response.put("success", false);
            response.put("message", "No se puede crear el TestAssignment porque el usuario no es Admin o Teacher");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TestAssignment createdTest = testAssignmentService.save(testAssignment);
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
    public ResponseEntity<Map<String, Object>> deleteTestAssignment(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            testAssignmentService.deleteById(id);
            response.put("success", true);
            response.put("message", "Test eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTestAssignment(@PathVariable Long id, @RequestBody TestAssignment testAssignment) {
        Map<String, Object> response = new HashMap<>();

        Optional<TestAssignment> existingTestAssignment = testAssignmentService.findById(id);
        if (!existingTestAssignment.isPresent()) {
            response.put("success", false);
            response.put("message", "TestAssignment no encontrado");
            return ResponseEntity.badRequest().body(response);
        }

        TestAssignment updatedTestAssignment = existingTestAssignment.get();
        updatedTestAssignment.setReviewComplete(testAssignment.getReviewComplete());

        TestAssignment savedTestAssignment = testAssignmentService.save(updatedTestAssignment);
        response.put("success", true);
        response.put("data", savedTestAssignment);
        return ResponseEntity.ok(response);
    }
}

