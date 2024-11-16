package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.tempTestAssignment;
import dev4lphas.estramypyme.estramypyme_backend.repository.tempTestAssignmentRepository;

@Service
public class TestAssignmentService {

    @Autowired
    private tempTestAssignmentRepository TestAssignmentRepository;
    
        public List<tempTestAssignment> findAll() {
            return TestAssignmentRepository.findAll();
        }
    
        public List<tempTestAssignment> findByTestId(Long testId) {
            return TestAssignmentRepository.findByTestId(testId);
        }
    
        public Optional<tempTestAssignment> findById(Long Id) {
            return TestAssignmentRepository.findById(Id);
        }
    
        public tempTestAssignment save(tempTestAssignment testAssignment) {
            // Asegurarse de que isReview sea false al crear una nueva Review
            if (testAssignment.getId() == null) {
                testAssignment.setReviewComplete(false);
            }
            return TestAssignmentRepository.save(testAssignment);
        }
       
    
        public void deleteById(Long id) {
            TestAssignmentRepository.deleteById(id);
        }
    
       public tempTestAssignment update(tempTestAssignment testAssignment) {
            if (testAssignment.getId() == null) {
                throw new IllegalArgumentException("El ID del Test no puede ser nulo para actualizar");
            }
            return TestAssignmentRepository.save(testAssignment);
    }

  
}