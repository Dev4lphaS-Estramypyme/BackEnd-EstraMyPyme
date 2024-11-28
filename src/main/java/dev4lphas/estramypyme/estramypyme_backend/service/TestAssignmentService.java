package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.model.TestAssignment;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestAssignmentRepository;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestRepository;

@Service
public class TestAssignmentService {

    @Autowired
    private TestAssignmentRepository testAssignmentRepository;

    @Autowired
    private TestRepository testRepository;

    public List<TestAssignment> findAll() {
        return testAssignmentRepository.findAll();
    }

    public List<TestAssignment> findByTestId(Long testId) {
        return testAssignmentRepository.findByTestId(testId);
    }

    public Optional<TestAssignment> findById(Long id) {
        return testAssignmentRepository.findById(id);
    }

    public TestAssignment save(TestAssignment testAssignment) {
        // Asegurarse de que isReview sea false al crear una nueva Review
        if (testAssignment.getId() == null) {
            testAssignment.setReviewComplete(false);
        }
        return testAssignmentRepository.save(testAssignment);
    }

    public void deleteById(Long id) {
        testAssignmentRepository.deleteById(id);
    }

    public TestAssignment update(TestAssignment testAssignment) {
        if (testAssignment.getId() == null) {
            throw new IllegalArgumentException("El ID del Test no puede ser nulo para actualizar");
        }
        return testAssignmentRepository.save(testAssignment);
    }

    public boolean isReviewed(Long testId) {
        Optional<Test> test = testRepository.findById(testId);
        return test.isPresent() && test.get().getIsReviewed();
    }
}