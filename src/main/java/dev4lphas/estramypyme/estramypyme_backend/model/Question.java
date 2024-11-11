package dev4lphas.estramypyme.estramypyme_backend.model;


import java.time.LocalDate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

}
