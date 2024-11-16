package dev4lphas.estramypyme.estramypyme_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public enum RoleName {
        Admin,
        Student,
        Teacher
    }

    public Boolean isActive() {
        return active;
    }
}