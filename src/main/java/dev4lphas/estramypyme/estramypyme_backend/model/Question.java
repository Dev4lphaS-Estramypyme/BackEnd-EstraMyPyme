package dev4lphas.estramypyme.estramypyme_backend.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
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

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    // Método setAnswers
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}