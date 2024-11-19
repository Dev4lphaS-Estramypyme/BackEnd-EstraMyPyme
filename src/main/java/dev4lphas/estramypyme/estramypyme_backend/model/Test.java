package dev4lphas.estramypyme.estramypyme_backend.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private UserCompany company;

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    @Column(name = "is_reviewed", nullable = false)
    private Boolean isReviewed = false;

    public Boolean isReviewed() {
        return isReviewed;
    }
}