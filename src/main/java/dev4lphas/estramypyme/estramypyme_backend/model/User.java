package dev4lphas.estramypyme.estramypyme_backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, length = 255, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
    
   @Enumerated(EnumType.STRING)
   @Column(name = "role_name", nullable = false)
   private RoleName roleName;

   @OneToMany(mappedBy = "user")
   @JsonIgnore
   private List<testAssignment> testAssignments;
   
    public enum RoleName {
        Admin,
        Student,
        Teacher
    }
}
