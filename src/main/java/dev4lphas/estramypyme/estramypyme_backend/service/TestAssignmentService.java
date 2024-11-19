package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.TestAssignment;
import dev4lphas.estramypyme.estramypyme_backend.repository.TestAssignmentRepository;

@Service
public class TestAssignmentService {

    @Autowired
    private TestAssignmentRepository TestAssignmentRepository;
    
        public List<TestAssignment> findAll() {
            return TestAssignmentRepository.findAll();
        }
    
        public List<TestAssignment> findByTestId(Long testId) {
            return TestAssignmentRepository.findByTestId(testId);
        }
    
        public Optional<TestAssignment> findById(Long Id) {
            return TestAssignmentRepository.findById(Id);
        }
    
        public TestAssignment save(TestAssignment testAssignment) {
            // Asegurarse de que isReview sea false al crear una nueva Review
            if (testAssignment.getId() == null) {
                testAssignment.setReviewComplete(false);
            }
            return TestAssignmentRepository.save(testAssignment);
        }
       
    
        public void deleteById(Long id) {
            TestAssignmentRepository.deleteById(id);
        }
    
       public TestAssignment update(TestAssignment testAssignment) {
            if (testAssignment.getId() == null) {
                throw new IllegalArgumentException("El ID del Test no puede ser nulo para actualizar");
            }
            return TestAssignmentRepository.save(testAssignment);
    }

  
}