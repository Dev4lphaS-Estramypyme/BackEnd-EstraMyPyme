package dev4lphas.estramypyme.estramypyme_backend.model;



import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_assignments")
public class TestAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long testId;
    private Long userId;
    private Boolean reviewCompleted;
    @Column(updatable = false)

    private LocalDateTime assignmentDate;

    public TestAssignment() {
        this.reviewCompleted = false; // Establecer valor por defecto en el constructor
    }

    @PrePersist
    protected void onCreate() {
        assignmentDate = LocalDateTime.now();
        if (reviewCompleted == null) {
            reviewCompleted = false; // Asegurarse de que reviewCompleted esté en false
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getReviewComplete() {
        return reviewCompleted;
    }

    public void setReviewComplete(Boolean reviewCompleted) {
        this.reviewCompleted = reviewCompleted;
    }

    public LocalDateTime getDate() {
        return assignmentDate;
    }

    public void setDate(LocalDateTime assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}


